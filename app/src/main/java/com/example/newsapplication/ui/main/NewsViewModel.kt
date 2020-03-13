package com.example.newsapplication.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import androidx.databinding.ObservableField
import com.example.newsapplication.models.NewsTypes
import com.example.newsapplication.network.NewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val disposables = CompositeDisposable()
    val title = ObservableField<String>("Get Your News")
    val subHeader = ObservableField<String>("Attention")

    init {
        getTopHeadlines()
    }

    private fun getTopHeadlines() {
        disposables.add(newsRepository.getTopHeadlines()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(this::topHeadlinesError)
            .subscribe(this::setComponents)
        )
    }

    private fun topHeadlinesError(e: Throwable) {
        Log.d("Jose", "There has been an error ${e.message}")
    }

    private fun setComponents(newsResponse: NewsTypes.NewsResponse) {
        title.set(newsResponse.articles[0].title)
        subHeader.set(newsResponse.articles[0].author)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}
