package com.atlascc.kontribute.util

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageUtil(var context: Context) {

    fun loadImage(imageUrl: String, imageView: ImageView) {
        Picasso.with(context).load(imageUrl).into(imageView)
    }

    fun loadImageFromDrawable(imageUrl: Int, imageView: ImageView) {
        Picasso.with(context).load(imageUrl).into(imageView)
    }
}