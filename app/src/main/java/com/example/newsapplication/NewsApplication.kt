package com.example.newsapplication

import android.app.Application
import com.example.newsapplication.dagger.DaggerAppComponent

class NewsApplication: Application() {
    val appComponent = DaggerAppComponent.create()
}