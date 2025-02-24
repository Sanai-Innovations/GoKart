package com.sanai.gokart.presentation.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.sanai.gokart.R

fun ImageView.loadImage(
    imgUrl: String?,
    @DrawableRes placeholder: Int = R.drawable.ic_launcher_foreground
) {
    Glide.with(this)
        .load(imgUrl)
        .placeholder(placeholder)
        .into(this)
}