package com.example.newsapplication.network

import com.example.newsapplication.models.NewsTypes
import retrofit2.http.GET
import io.reactivex.Observable

const val baseUrl = "http://newsapi.org/"

interface NewsApi {
    @GET("v2/top-headlines?country=us")
    fun getTopHeadlines(): Observable<NewsTypes.NewsResponse>
}