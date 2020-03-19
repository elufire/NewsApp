package com.example.newsapplication.ui.main

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.example.newsapplication.R
import com.example.newsapplication.models.NewsTypes

class ArticleViewModel constructor(
    article: NewsTypes.Article
) {
    val title = ObservableField<String>("")
    val author = ObservableField<String>("")
    val imageUrl = ObservableField<String>("")
    val byAuthor = ObservableInt(R.string.by_author)

    init{
        title.set(article.title)
        author.set(article.author)
        imageUrl.set(article.urlToImage)
    }
}