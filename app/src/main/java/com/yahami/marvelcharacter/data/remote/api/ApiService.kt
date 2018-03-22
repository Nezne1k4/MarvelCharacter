package com.yahami.marvelcharacter.data.remote.api

import com.yahami.marvelcharacter.data.remote.dto.CharacterMarvelDto
import com.yahami.marvelcharacter.data.remote.dto.DataWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Nezneika on 3/22/18.
 */
interface ApiService {
    @GET("characters")
    fun getCharacters(
            @Query("offset") offset: Int?,
            @Query("limit") limit: Int?
    ): Single<DataWrapper<List<CharacterMarvelDto>>>
}