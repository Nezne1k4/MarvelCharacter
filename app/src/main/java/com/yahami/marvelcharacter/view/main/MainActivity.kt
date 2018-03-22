package com.yahami.marvelcharacter.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.yahami.marvelcharacter.R
import com.yahami.marvelcharacter.model.MarvelCharacter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val characters = listOf(
            MarvelCharacter("Captain Winter", "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"),
            MarvelCharacter("Iron man X", "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg"),
            MarvelCharacter("Iron man X", "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg"),
            MarvelCharacter("Iron man X", "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg"),
            MarvelCharacter("Iron man X", "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg"),
            MarvelCharacter("Iron man X", "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg"),
            MarvelCharacter("Iron man X", "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg"),
            MarvelCharacter("Iron man X", "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg"),
            MarvelCharacter("Iron man X", "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg"),
            MarvelCharacter("Iron man X", "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg"),
            MarvelCharacter("Iron man X", "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg"),
            MarvelCharacter("Iron man X", "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        val categoryItemAdapters = characters.map(::CharacterItemAdapter) // 4
        recyclerView.adapter = MainListAdapter(categoryItemAdapters)

    }
}
