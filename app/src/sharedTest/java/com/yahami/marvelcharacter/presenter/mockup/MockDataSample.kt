package com.yahami.marvelcharacter.presenter.mockup

import com.yahami.marvelcharacter.model.MarvelCharacter

object MockDataSample {
    val sampleCharacter = MarvelCharacter("ExampleName", "ExampleImageUrl")

    val sampleCharacterList = listOf(
            sampleCharacter,
            MarvelCharacter("Name1", "ImageUrl1"),
            MarvelCharacter("Name2", "ImageUrl2")
    )
}
