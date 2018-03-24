package com.yahami.marvelcharacter.view.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.yahami.marvelcharacter.R
import com.yahami.marvelcharacter.model.MarvelCharacter
import com.yahami.marvelcharacter.view.ext.bindView
import com.yahami.marvelcharacter.view.ext.loadImage
import com.yahami.marvelcharacter.view.list.ItemAdapter

class CharacterItemAdapter(
        private val character: MarvelCharacter,
        private val operatorOnClick: (MarvelCharacter) -> Unit = {})
    : ItemAdapter<CharacterItemAdapter.ViewHolder>(R.layout.item_character) {
    override fun onCreateViewHolder(itemView: View): ViewHolder {
        return ViewHolder(itemView)
    }

    override fun ViewHolder.onBindViewHolder() {
        textView.text = character.name
        imageView.loadImage(character.imageUrl)
        // clickListener
        itemView.setOnClickListener { operatorOnClick(character) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView by bindView<TextView>(R.id.textView)
        val imageView by bindView<ImageView>(R.id.imageView)
    }
}
