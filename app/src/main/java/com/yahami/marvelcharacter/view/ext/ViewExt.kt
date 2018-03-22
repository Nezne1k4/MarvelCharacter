package com.yahami.marvelcharacter.view.ext

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Load image into ImageView using Glide
 */
fun ImageView.loadImage(photoUrl: String, centerCropped: Boolean = false) {
    Glide.with(context)
            .load(photoUrl)
            .apply { if (centerCropped) centerCrop() }
            .into(this)
}

// RecyclerView ext
fun <T : View> RecyclerView.ViewHolder.bindView(viewId: Int) = lazy { itemView.findViewById<T>(viewId) }