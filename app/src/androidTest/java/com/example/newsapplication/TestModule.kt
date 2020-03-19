package com.example.newsapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapplication.dagger.NetworkModule
import com.example.newsapplication.dagger.NewsModule
import com.example.newsapplication.network.NewsApi
import com.example.newsapplication.network.NewsRepository
import com.example.newsapplication.ui.main.NewsViewModel
import com.example.newsapplication.ui.main.ViewModelFactory
import io.mockk.mockk
import okhttp3.OkHttpClient

class TestModule : NetworkModule(){

//    override fun provideOkhttpClient(): OkHttpClient = mockk()
//
//    override fun providesNewsRepository(newsApi: NewsApi): NewsRepository = mockk()

    override fun provideNewsApi(okHttpClient: OkHttpClient): NewsApi = mockk()

}