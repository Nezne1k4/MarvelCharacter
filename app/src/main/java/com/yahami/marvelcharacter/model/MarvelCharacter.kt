package com.yahami.marvelcharacter.model

import android.os.Parcelable
import com.yahami.marvelcharacter.data.remote.dto.CharacterMarvelDto
import kotlinx.android.parcel.Parcelize

@Parcelize
class MarvelCharacter(val name: String, val imageUrl: String) : Parcelable {
    constructor(dto: CharacterMarvelDto) : this(
            name = dto.name,
            imageUrl = dto.imageUrl
    )
}