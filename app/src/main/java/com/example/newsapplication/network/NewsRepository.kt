package com.example.newsapplication.network

import com.example.newsapplication.models.NewsTypes
import io.reactivex.Observable


interface NewsRepository {

    fun getTopHeadlines(): Observable<NewsTypes.NewsResponse>
}