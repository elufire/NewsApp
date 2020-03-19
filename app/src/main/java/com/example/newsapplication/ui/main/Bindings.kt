package com.example.newsapplication.ui.main

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication.R
import com.example.newsapplication.models.NewsTypes

class Bindings {
    companion object{
        @JvmStatic
        @BindingAdapter("android:imageUrl")
        fun loadNetworkImage(view: ImageView, imageUrl: String) {
            if(imageUrl.isEmpty()){
                view.setImageResource(R.drawable.ic_sentiment_very_dissatisfied)
                return
            }
            Glide.with(view.context).load(imageUrl).centerCrop().into(view)
        }

        @JvmStatic
        @BindingAdapter("android:recyclerComponents")
        fun setRecyclerComponents(view: RecyclerView,
                                  articles: List<NewsTypes.Article>) {
            view.layoutManager = LinearLayoutManager(view.context,
                LinearLayoutManager.VERTICAL,
                false)
            view.adapter = ArticleRecyclerViewAdapter(articles)
        }
    }
}