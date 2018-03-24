package com.yahami.marvelcharacter.view.character

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.yahami.marvelcharacter.R
import com.yahami.marvelcharacter.model.MarvelCharacter
import com.yahami.marvelcharacter.view.ext.extra
import com.yahami.marvelcharacter.view.ext.loadImage
import kotlinx.android.synthetic.main.activity_character_profile.*

class CharacterProfileActivity : AppCompatActivity() {

    // this is magic :)
    val character: MarvelCharacter by extra(CHARACTER_ARG)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_profile)

        setUpToolbar()

        initData()
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // on back button click
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when {
        item.itemId == android.R.id.home -> onBackPressed().let { true }
        else -> super.onOptionsItemSelected(item)
    }

    private fun initData() {
        supportActionBar?.title = character.name
        headerView.loadImage(character.imageUrl, centerCropped = true)

        descriptionView.text = character.description
        occurrencesView.text = makeOccurrencesText()
    }

    private fun makeOccurrencesText(): String = "" // 1, 2
            .addList(R.string.occurrences_comics_list_introduction, character.comics)
            .addList(R.string.occurrences_series_list_introduction, character.series)
            .addList(R.string.occurrences_stories_list_introduction, character.stories)
            .addList(R.string.occurrences_events_list_introduction, character.events)

    private fun String.addList(resourceStringId: Int, list: List<String>): String { // 3
        if (list.isEmpty()) return this
        val introductionText = getString(resourceStringId)
        val listText = list.joinToString(transform = { " $bullet $it" }, separator = "\n")
        return this + "$introductionText\n$listText\n\n"
    }

    companion object {
        private const val bullet = '\u2022'
        private const val CHARACTER_ARG = "com.yahami.marvelcharacter.view.character.CharacterProfileActivity.CharacterArgKey"

        fun start(context: Context, character: MarvelCharacter) {
            val intent = Intent(context, CharacterProfileActivity::class.java)
                    .apply {
                        // character implement Parcelable
                        putExtra(CHARACTER_ARG, character)
                    }
            context.startActivity(intent)
        }

    }
}
