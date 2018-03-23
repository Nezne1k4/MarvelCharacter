package com.yahami.marvelcharacter.presenter.mockup

import com.yahami.marvelcharacter.model.MarvelCharacter
import com.yahami.marvelcharacter.view.main.MainView

class MockMainView(
        override var refresh: Boolean = false,
        var mockShow: (items: List<MarvelCharacter>) -> Unit = {},
        val mockShowError: (error: Throwable) -> Unit = {}
) : MainView {

    override fun show(items: List<MarvelCharacter>) {
        mockShow(items)
    }

    override fun showError(error: Throwable) {
        mockShowError(error)
    }
}