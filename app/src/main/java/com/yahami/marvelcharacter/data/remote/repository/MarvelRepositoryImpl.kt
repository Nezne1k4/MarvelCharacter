package com.yahami.marvelcharacter.data.remote.repository

import com.yahami.marvelcharacter.data.remote.api.ApiService
import com.yahami.marvelcharacter.data.remote.api.retrofit
import com.yahami.marvelcharacter.model.MarvelCharacter
import io.reactivex.Single

class MarvelRepositoryImpl : MarvelRepository {

    val api = retrofit.create(ApiService::class.java)

    // converter layer to convert DTO to POJO MarvelCharacter
    override fun getAllCharacters(searchText: String?): Single<List<MarvelCharacter>> =
            api.getCharacters(
                    searchText = searchText,
                    offset = 0,
                    limit = elementsOnListLimit)
                    .map {
                        it.data?.results.orEmpty().map(::MarvelCharacter)
                    }

    companion object {
        const val elementsOnListLimit = 50
    }
}