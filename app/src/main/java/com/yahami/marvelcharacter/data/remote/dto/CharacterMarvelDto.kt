package com.yahami.marvelcharacter.data.remote.dto

class CharacterMarvelDto {

    // For values that might not be provided, we should set a default value.
    // Values that are mandatory might be prefixed with 'lateinit' instead.
    lateinit var name: String
    lateinit var thumbnail: ImageDto

    val imageUrl: String
        get() = thumbnail.completeImagePath
}