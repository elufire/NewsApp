package com.example.newsapplication.dagger

import com.example.newsapplication.network.NewsApi
import com.example.newsapplication.network.NewsRepository
import com.example.newsapplication.network.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Named
import javax.inject.Singleton

const val token = "a2bb244bfda74fbc9ca8bad823a7ea31"

@Module
open class NetworkModule {

    @Provides
    open fun provideOkhttpClient(): OkHttpClient =
        OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        }.build()

    @Singleton
    @Provides
    open fun provideNewsApi(okHttpClient: OkHttpClient): NewsApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(NewsApi::class.java)

    @Singleton
    @Provides
    open fun providesNewsRepository(newsApi: NewsApi): NewsRepository = NewsRepositoryImpl(newsApi)
}