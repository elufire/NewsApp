package com.example.newsapplication

import android.content.res.AssetManager
import com.example.newsapplication.models.NewsTypes
import com.example.newsapplication.network.NewsRepository
import com.example.newsapplication.ui.main.NewsViewModel
import com.google.gson.Gson
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class NewsViewModelTest {
    lateinit var newsViewModel: NewsViewModel
    lateinit var news: NewsTypes.NewsResponse
    @MockK
    lateinit var newsRepository: NewsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        newsViewModel = NewsViewModel(newsRepository)
        news = Gson().fromJson<NewsTypes.NewsResponse>(this.javaClass.classLoader
            ?.getResource("NewsResponse.json")?.readText(), NewsTypes.NewsResponse::class.java)
    }

    @Test
    fun `given top headlines event triggered then call repo for data`() {
//        System.out.println(news)
        every{newsRepository.getTopHeadlines()} returns Observable.just(news)
        newsViewModel.getTopHeadlines()
        assertEquals("Adam Nossiter, Raphael Minder, Elian Peltier",
            newsViewModel.articles[0].author)
        assertEquals("Nytimes.com", newsViewModel.articles[0].source.name)
        assertEquals("Shutdowns Spread Across Europe as Spain, France Order Broad " +
                "Restrictions - The New York Times",
            newsViewModel.articles[0].title)
    }

    @Test
    fun `given search for articles then call repo for data`() {
        val corona = "corona"
        every { newsRepository.searchArticles(corona) } returns Observable.just(news)
        newsViewModel.searchArticles(corona)
        assertEquals("Michael Collins, Courtney Subramanian, Deirdre Shesgreen",
            newsViewModel.articles[1].author)
        assertEquals("USA Today", newsViewModel.articles[1].source.name)
        assertEquals("Trump tests negative for coronavirus, White House doctor " +
                "says - USA TODAY",
            newsViewModel.articles[1].title)
    }
}