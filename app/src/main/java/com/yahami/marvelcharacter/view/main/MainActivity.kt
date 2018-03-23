package com.yahami.marvelcharacter.view.main

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.yahami.marvelcharacter.R
import com.yahami.marvelcharacter.data.remote.repository.MarvelRepository
import com.yahami.marvelcharacter.model.MarvelCharacter
import com.yahami.marvelcharacter.presenter.MainPresenter
import com.yahami.marvelcharacter.view.common.BaseActivityWithPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivityWithPresenter(), MainView {

    override val presenter by lazy { MainPresenter(this, MarvelRepository.provides()) }

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

    /**
     * functions of MainView
     */
    override var refresh: Boolean
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override fun show(items: List<MarvelCharacter>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * /functions of MainView
     */
}
