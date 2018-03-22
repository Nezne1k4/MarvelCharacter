package com.yahami.marvelcharacter.view.list

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * To display a list of elements using RecyclerView, we need to implement both a RecyclerView list and an item adapter.
 * A list adapter is used to manage all elements in a list, while an item adapter is an adapter for a single item type.
 * Here, we need only one item adapter, because we display a single type of items.
 * It is, however, good practice to assume that in future there might be other kinds of elements on this list, for example, comics or ads.
 * The same with the list adapter - we need only one in this example, but in most projects there is more than a single list,
 * and it is better to extract common behavior into a single abstract class.
 */
abstract class ItemAdapter<T : RecyclerView.ViewHolder>(val layoutId: Int) {

    abstract fun onCreateViewHolder(itemView: View): T

    abstract fun T.onBindViewHolder()

    @Suppress("UNCHECKED_CAST")
    fun bindViewHolder(holder: RecyclerView.ViewHolder) {
        (holder as T).onBindViewHolder()
    }
}