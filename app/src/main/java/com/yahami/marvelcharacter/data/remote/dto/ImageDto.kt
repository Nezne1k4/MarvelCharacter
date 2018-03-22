package com.yahami.marvelcharacter.data.remote.dto

class ImageDto {

    // For values that might not be provided, we should set a default value.
    // Values that are mandatory might be prefixed with 'lateinit' instead.
    lateinit var path: String
    lateinit var extension: String

    val completeImagePath: String
        get() = "$path.$extension"
}