package com.example.newsapplication.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapplication.ui.main.NewsViewModel
import com.example.newsapplication.ui.main.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

const val baseUrl = "http://newsapi.org/"

@Module
abstract class NewsModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    internal abstract fun provideNewsViewModel(viewModel: NewsViewModel): ViewModel
}