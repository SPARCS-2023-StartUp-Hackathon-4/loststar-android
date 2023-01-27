package com.sparcs.loststar.util

import android.widget.ImageView
import com.bumptech.glide.Glide

object GlideUtil {
    fun loadImage(view: ImageView, imageUrl: String?) {
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(view)
                .load(imageUrl)
                .into(view)
        }
    }
}