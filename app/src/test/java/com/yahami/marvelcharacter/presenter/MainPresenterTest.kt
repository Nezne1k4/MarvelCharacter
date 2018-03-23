package com.yahami.marvelcharacter.presenter

import com.yahami.marvelcharacter.data.remote.repository.MarvelRepository
import com.yahami.marvelcharacter.model.MarvelCharacter
import com.yahami.marvelcharacter.view.main.MainView
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

    private fun assertOnAction(action: MainPresenter.() -> Unit)
            = PresenterActionAssertion(action)

    // MainPresenter.() means any functions in MainPresenter
    private class PresenterActionAssertion(val operateFuncInPresenter: MainPresenter.() -> Unit) {

        fun thereIsSameListDisplayed() {
            // arrange
            val exampleCharacterList = listOf(
                    MarvelCharacter("ExampleName", "ExampleImageUrl"),
                    MarvelCharacter("Name1", "ImageUrl1"),
                    MarvelCharacter("Name2", "ImageUrl2")
            )

            var displayedList: List<MarvelCharacter>? = null

            // mocking view
            val view = object : MainView {
                override var refresh: Boolean = false

                override fun show(items: List<MarvelCharacter>) {
                    displayedList = items // target succeed
                }

                override fun showError(error: Throwable) {
                    fail() // assume it fails if show error
                }
            }

            // mocking repository
            val marvelRepository = object : MarvelRepository {
                override fun getAllCharacters(): Single<List<MarvelCharacter>>
                        = Single.just(exampleCharacterList)
            }

            // mocking Presenter
            val mainPresenter = MainPresenter(view, marvelRepository)

            // action
            mainPresenter.operateFuncInPresenter()

            // assert
            assertEquals(exampleCharacterList, displayedList)
        }
    }

}