package com.example.newsapplication.network

import com.example.newsapplication.models.NewsTypes
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/top-headlines?country=us")
    fun getTopHeadlines(): Observable<NewsTypes.NewsResponse>

    @GET("v2/everything")
    fun searchArticles(@Query("q")query: String): Observable<NewsTypes.NewsResponse>
}