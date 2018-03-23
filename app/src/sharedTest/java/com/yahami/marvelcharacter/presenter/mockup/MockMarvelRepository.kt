package com.yahami.marvelcharacter.presenter.mockup

import com.yahami.marvelcharacter.data.remote.repository.MarvelRepository
import com.yahami.marvelcharacter.model.MarvelCharacter
import io.reactivex.Single

class MockMarvelRepository(private val onGetFakeCharacters: () -> Single<List<MarvelCharacter>>)
    : MarvelRepository {
    override fun getAllCharacters() = onGetFakeCharacters()
}