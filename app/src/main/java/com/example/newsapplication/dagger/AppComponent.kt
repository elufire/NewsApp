package com.example.newsapplication.dagger

import com.example.newsapplication.ui.main.NewsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NewsModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(fragment: NewsFragment)
}