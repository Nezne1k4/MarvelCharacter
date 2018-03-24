package com.yahami.marvelcharacter.presenter

import com.yahami.marvelcharacter.data.remote.repository.MarvelRepository
import com.yahami.marvelcharacter.utils.applySchedulers
import com.yahami.marvelcharacter.utils.plusAssign
import com.yahami.marvelcharacter.view.main.MainView

class MainPresenter(val view: MainView, private val repository: MarvelRepository) : BasePresenterImp() {

    fun onViewCreated() {
        loadAllCharacters()
    }

    fun onRefresh() {
        loadAllCharacters()
    }

    fun onSearchChanged(text: String) {
        loadAllCharacters(text)
    }

    private fun loadAllCharacters(searchText: String? = null) {
        val qualifiedSearchQuery = if (searchText.isNullOrBlank()) null else searchText
        // subscriptions for better control memory leak
        // subscriptions is in scope of BasePresenterImp
        subscriptions += repository.getAllCharacters(qualifiedSearchQuery)
                .applySchedulers()
                .doOnSubscribe { view.refresh = true }
                .doFinally { view.refresh = false }
                .subscribe(
                        { view.show(it) }, // what is a Consumer ?
                        { view.showError(it) }
                )
    }
}