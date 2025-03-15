package com.sanai.gokart.presentation.util

import android.graphics.Paint
import android.widget.ImageView
import android.widget.TextView
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

fun TextView.showStrikeThrough(show: Boolean) {
    paintFlags = if (show) paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    else paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
}