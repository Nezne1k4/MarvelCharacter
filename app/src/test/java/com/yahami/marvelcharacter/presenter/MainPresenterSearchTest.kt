package com.yahami.marvelcharacter.presenter

import com.yahami.marvelcharacter.presenter.mockup.MockMainView
import com.yahami.marvelcharacter.presenter.mockup.MockMarvelRepository
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Test onSearchChanged() in MainPresenter
 */
@Suppress("IllegalIdentifier")
class MainPresenterSearchTest {

    @Before
    fun setUp() {
        // init Rx environment for testing
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `When view is created, then search query is null`() {
        PresenterActionAssertion({ onViewCreated() }) searchQueryIsEqualTo (null)
    }

    @Test
    fun `For blank text, there is request with null query`() {
        for (emptyText in listOf("", "   ", "       "))
            PresenterActionAssertion { onSearchChanged(emptyText) } searchQueryIsEqualTo null
    }

    @Test
    fun `When text is changed, then we are searching for new query`() {
        for (text in listOf("KKO", "HJ HJ", "And so what?")) {
            PresenterActionAssertion { onSearchChanged(text) } searchQueryIsEqualTo text // with help from infix
        }
    }

    private class PresenterActionAssertion(val actionOnPresenter:
                                           MainPresenter.() -> Unit) {

        infix fun searchQueryIsEqualTo(expectedQuery: String?) {
            var checkApplied = false

            val view = MockMainView(mockShowError = { fail() })

            val repository = MockMarvelRepository {searchText ->
                assertEquals(expectedQuery, searchText)
                checkApplied = true
                Single.never()
            }
            val mainPresenter = MainPresenter(view, repository)
            mainPresenter.actionOnPresenter()
            assertTrue(checkApplied)
        }
    }
}