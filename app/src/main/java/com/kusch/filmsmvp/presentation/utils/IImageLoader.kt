package com.kusch.filmsmvp.presentation.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.kusch.filmsmvp.R
import javax.inject.Inject

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}

class GlideImageLoader @Inject constructor() : IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url).centerCrop()
            .placeholder(R.drawable.ic_baseline_block_24)
            .into(container)
    }
}

