package com.example.newsapplication.ui.main

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class ImageBindings {

    @BindingAdapter("android:imageUrl")
    fun loadNetworkImage(view: ImageView, imageUrl: String) {
        Glide.with(view.context).load(imageUrl).into(view)
    }
}