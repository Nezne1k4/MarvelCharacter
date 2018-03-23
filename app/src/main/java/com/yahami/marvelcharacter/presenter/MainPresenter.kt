package com.yahami.marvelcharacter.presenter

import com.yahami.marvelcharacter.data.remote.repository.MarvelRepository
import com.yahami.marvelcharacter.view.main.MainView

class MainPresenter(val view: MainView, val repository: MarvelRepository) {

    fun onViewCreated() {
        loadAllCharacters()
    }

    fun onRefresh() {
        loadAllCharacters()
    }

    private fun loadAllCharacters() {
        repository.getAllCharacters()
                .subscribe(
                        { items -> view.show(items) }, // what is a Consumer ?
                        { error -> view.showError(error) }
                )
    }
}