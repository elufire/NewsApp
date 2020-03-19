package com.example.newsapplication.ui.main

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import com.example.newsapplication.models.NewsTypes
import com.example.newsapplication.network.NewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val disposables = CompositeDisposable()
    val articles = ObservableArrayList<NewsTypes.Article>()

    fun getTopHeadlines() {
        disposables.add(newsRepository.getTopHeadlines()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {newsResponse -> setComponents(newsResponse)},
                {error -> onArticlesError(error)})
        )
    }

    fun searchArticles(query: String) {
        disposables.add(newsRepository.searchArticles(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {newsResponse -> setComponents(newsResponse)},
                {error -> onArticlesError(error)}
            )
        )
    }

    private fun onArticlesError(e: Throwable) {
        Log.d("Jose", "onError ${e.message}")
    }

    private fun setComponents(newsResponse: NewsTypes.NewsResponse) {
        articles.clear()
        articles.addAll(newsResponse.articles)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}
