package com.yahami.marvelcharacter.presenter.mockup

import com.yahami.marvelcharacter.model.MarvelCharacter

object MockDataSample {
    val sampleCharacter = MarvelCharacter("ExampleName", "ExampleImageUrl")

    val sampleCharacterList = listOf(
            sampleCharacter,
            MarvelCharacter("Captain Winter", "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"),
            MarvelCharacter("Iron man X", "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg")
    )
}
