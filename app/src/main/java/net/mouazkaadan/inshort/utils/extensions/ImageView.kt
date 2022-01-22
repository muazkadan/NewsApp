package net.mouazkaadan.inshort.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(
    imageUrl: Any
) {
    Glide.with(context)
        .load(imageUrl)
        .into(this)
}
