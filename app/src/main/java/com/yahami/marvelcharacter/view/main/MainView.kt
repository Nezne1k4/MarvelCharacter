package com.yahami.marvelcharacter.view.main

import com.yahami.marvelcharacter.model.MarvelCharacter

interface MainView {
    var refresh: Boolean
    fun show(items: List<MarvelCharacter>)
    fun showError(error: Throwable)
}