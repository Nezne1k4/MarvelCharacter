package com.yahami.marvelcharacter.presenter

import com.yahami.marvelcharacter.data.remote.repository.MarvelRepository
import com.yahami.marvelcharacter.utils.applySchedulers
import com.yahami.marvelcharacter.utils.plusAssign
import com.yahami.marvelcharacter.view.main.MainView

class MainPresenter(val view: MainView, val repository: MarvelRepository) : BasePresenterImp() {

    fun onViewCreated() {
        loadAllCharacters()
    }

    fun onRefresh() {
        loadAllCharacters()
    }

    private fun loadAllCharacters() {
        // subscriptions for better control memory leak
        // subscriptions is in scope of BasePresenterImp
        subscriptions += repository.getAllCharacters()
                .applySchedulers()
                .doOnSubscribe { view.refresh = true }
                .doFinally { view.refresh = false }
                .subscribe(
                        { view.show(it) }, // what is a Consumer ?
                        { view.showError(it) }
                )
    }
}