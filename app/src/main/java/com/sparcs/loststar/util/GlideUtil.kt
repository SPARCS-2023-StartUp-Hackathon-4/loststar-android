package com.sparcs.loststar.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

object GlideUtil {
    fun loadImage(view: ImageView, imageUrl: String?) {
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(view)
                .load(imageUrl)
                .centerCrop()
                .into(view)
        }
    }

    fun loadRadiusImage(view: ImageView, imageUrl: String?, radius: Int) {
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(view)
                .load(imageUrl)
                .transform(CenterCrop(), RoundedCorners(radius))
                .into(view)
        }
    }
}