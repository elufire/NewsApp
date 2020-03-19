package com.example.newsapplication

import com.example.newsapplication.dagger.AppComponent
import com.example.newsapplication.dagger.NetworkModule
import com.example.newsapplication.dagger.NewsModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NewsModule::class, NetworkModule::class])
interface TestAppComponent: AppComponent {
    fun inject(test: MainActivityInstrumentedTest)
}