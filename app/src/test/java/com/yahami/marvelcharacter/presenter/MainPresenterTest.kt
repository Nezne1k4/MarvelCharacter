package com.yahami.marvelcharacter.presenter

import com.yahami.marvelcharacter.model.MarvelCharacter
import com.yahami.marvelcharacter.presenter.mockup.MockDataSample
import com.yahami.marvelcharacter.presenter.mockup.MockMainView
import com.yahami.marvelcharacter.presenter.mockup.MockMarvelRepository
import io.reactivex.Single
import org.junit.Test

import org.junit.Assert.*

/**
 * IllegalIdentifier allow description replace function name
 */
@Suppress("IllegalIdentifier")
class MainPresenterTest {

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

}