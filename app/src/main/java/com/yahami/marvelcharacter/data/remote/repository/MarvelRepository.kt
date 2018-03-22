package com.yahami.marvelcharacter.data.remote.repository

import com.yahami.marvelcharacter.model.MarvelCharacter
import io.reactivex.Single

/**
 * he biggest problem is that we now need to operate on DTO objects instead of on our own data model objects.
 * For mapping, we should define an additional layer. The Repository Pattern is used for this purpose.
 * This pattern is also really helpful when we are implementing unit tests because we can mock the repository
 * instead of the whole API definition.
 */
interface MarvelRepository {
    fun getAllCharacters(): Single<List<MarvelCharacter>>

    companion object : Provider<MarvelRepository>() {
        override fun provides(): MarvelRepository {
            return MarvelRepositoryImpl()
        }
    }
}