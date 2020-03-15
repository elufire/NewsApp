package com.example.newsapplication.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.R
import com.example.newsapplication.databinding.ArticleLayoutBinding
import com.example.newsapplication.models.NewsTypes

class ArticleRecyclerViewAdapter constructor(
    private val articles: List<NewsTypes.Article>
):  RecyclerView.Adapter<ArticleRecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutArticleBinding: ArticleLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.article_layout, parent, false
        )
        return ViewHolder(layoutArticleBinding)

    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.viewModel = ArticleViewModel(articles[holder.adapterPosition])
    }

    inner class ViewHolder constructor(@param:NonNull internal var itemBinding: ArticleLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

}
