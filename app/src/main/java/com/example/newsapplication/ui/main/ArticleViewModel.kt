package com.example.newsapplication.ui.main

import androidx.databinding.ObservableField
import com.example.newsapplication.models.NewsTypes

class ArticleViewModel constructor(
    private val article: NewsTypes.Article
) {
    val title = ObservableField<String>("")
    val author = ObservableField<String>("")
    val imageUrl = ObservableField<String>("")

    init{
        title.set(article.title)
        author.set(article.author)
        imageUrl.set(article.imageUrl)
    }


}