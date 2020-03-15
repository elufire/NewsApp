package com.example.newsapplication.network

import com.example.newsapplication.models.NewsTypes
import io.reactivex.Observable
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
): NewsRepository {
    override fun getTopHeadlines(): Observable<NewsTypes.NewsResponse> {
        return newsApi.getTopHeadlines()
    }

    override fun searchArticles(query: String): Observable<NewsTypes.NewsResponse> {
        return newsApi.searchArticles(query)
    }
}