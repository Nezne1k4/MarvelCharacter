package com.yahami.marvelcharacter.presenter

import com.yahami.marvelcharacter.data.remote.repository.MarvelRepository
import com.yahami.marvelcharacter.view.main.MainView

class MainPresenter(val view: MainView, val repository: MarvelRepository) {

    fun onViewCreated() {
    }

    fun onRefresh() {
    }
}