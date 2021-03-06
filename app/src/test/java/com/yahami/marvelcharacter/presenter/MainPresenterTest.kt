package com.yahami.marvelcharacter.presenter

import com.yahami.marvelcharacter.model.MarvelCharacter
import com.yahami.marvelcharacter.presenter.mockup.MockDataSample
import com.yahami.marvelcharacter.presenter.mockup.MockMainView
import com.yahami.marvelcharacter.presenter.mockup.MockMarvelRepository
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * IllegalIdentifier allow description replace function name
 */
@Suppress("IllegalIdentifier")
class MainPresenterTest {

    @Before
    fun setUp() {
        // init Rx environment for testing
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `After view was created, list of characters is loaded and displayed`() {
        assertOnAction { onViewCreated() }.thereIsSameListDisplayed()
    }

    @Test
    fun `New list is shown after view was refreshed`() {
        assertOnAction { onRefresh() }.thereIsSameListDisplayed()
    }

    private fun assertOnAction(action: MainPresenter.() -> Unit) = PresenterActionAssertion(action)

    // MainPresenter.() means any functions in MainPresenter
    private class PresenterActionAssertion(val operateFuncInPresenter: MainPresenter.() -> Unit) {

        fun thereIsSameListDisplayed() {
            // arrange
            var displayedList: List<MarvelCharacter>? = null

            // mocking view
            val mockView = MockMainView(
                    mockShow = { items -> displayedList = items },
                    mockShowError = { fail() }
            )

            // mocking repository
            val exampleCharacterList = MockDataSample.sampleCharacterList
            val mockRepository = MockMarvelRepository { Single.just(exampleCharacterList) }
            //val mockRepository = MockMarvelRepository { throw Throwable("404 Not found error") }

            // mocking Presenter
            val mainPresenter = MainPresenter(mockView, mockRepository)

            // action
            mainPresenter.operateFuncInPresenter()

            // assert
            assertEquals(exampleCharacterList, displayedList)
        }
    }

    @Test
    fun `When API returns error, it is displayed error on view`() {
        // given
        val someError = Error("API result error")
        var errorDisplayed: Throwable? = null

        val repository = MockMarvelRepository { Single.error(someError) }

        val view = MockMainView(
                mockShow = { _ -> fail() },
                mockShowError = { errorDisplayed = it }
        )

        // when
        val mainPresenter = MainPresenter(view, repository)
        mainPresenter.onViewCreated()

        // then
        assertEquals(someError, errorDisplayed)
    }

    @Test
    fun `When presenter is waiting for response, refresh is displayed`() {
        // given
        val view = MockMainView(refresh = false) // default refresh = false

        val repository = MockMarvelRepository {
            Single.fromCallable {
                // then
                // check when api is running
                assertTrue(view.refresh)
                MockDataSample.sampleCharacterList
            }
        }

        view.mockShow = {
            // then
            // check when api returns data to view
            _ -> assertTrue(view.refresh)
        }
        val mainPresenter = MainPresenter(view, repository)

        // when
        mainPresenter.onViewCreated()

        // then
        // check when all data shown
        assertFalse(view.refresh)
    }

}