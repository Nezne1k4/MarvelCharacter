package com.yahami.marvelcharacter.model

import com.yahami.marvelcharacter.data.remote.dto.CharacterMarvelDto

/**
 * Created by Nezneika on 3/22/18.
 */
class MarvelCharacter(val name: String, val imageUrl: String) {
    constructor(dto: CharacterMarvelDto) : this(
            name = dto.name,
            imageUrl = dto.imageUrl
    )
}