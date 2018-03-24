package com.yahami.marvelcharacter.view.main

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.text.Editable
import android.text.TextWatcher
import com.yahami.marvelcharacter.R
import com.yahami.marvelcharacter.data.remote.repository.MarvelRepository
import com.yahami.marvelcharacter.model.MarvelCharacter
import com.yahami.marvelcharacter.presenter.MainPresenter
import com.yahami.marvelcharacter.view.character.CharacterProfileActivity
import com.yahami.marvelcharacter.view.common.BaseActivityWithPresenter
import com.yahami.marvelcharacter.view.ext.bindToSwipeRefresh
import com.yahami.marvelcharacter.view.ext.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivityWithPresenter(), MainView {

    // init the presenter value
    override val presenter by lazy { MainPresenter(this, MarvelRepository.provides()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // list
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        // load testing data
        //val categoryItemAdapters = testCharactersList.map(::CharacterItemAdapter)
        //recyclerView.adapter = MainListAdapter(categoryItemAdapters)

        // swipeRefreshView
        swipeRefreshView.setOnRefreshListener { presenter.onRefresh() }

        // searchView
        searchView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.onSearchChanged(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        // first load data
        presenter.onViewCreated()
    }

    /**
     * functions of MainView
     */
//    override var refresh: Boolean
//        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
//        set(value) {}

    override var refresh by bindToSwipeRefresh(R.id.swipeRefreshView)

    override fun show(items: List<MarvelCharacter>) {
        //val categoryItemAdapters = items.map(::CharacterItemAdapter)
        val categoryItemAdapters = items.map(::createCategoryItemAdapter)
        recyclerView.adapter = MainListAdapter(categoryItemAdapters)
    }

    override fun showError(error: Throwable) {
        toast("Error: ${error.message}")
        error.printStackTrace()
    }

    private fun createCategoryItemAdapter(character: MarvelCharacter) =
            CharacterItemAdapter(character, { showHeroProfile(character) })

    /**
     * Action on item adapter clicking
     */
    private fun showHeroProfile(character: MarvelCharacter) {
        CharacterProfileActivity.start(this, character)
    }

    /**
     * /functions of MainView
     */

//    private val testCharactersList = listOf(
//            MarvelCharacter("Captain Winter", "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"),
//            MarvelCharacter("Iron man X", "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg")
//    )
}
