package com.example.newsapplication

import android.app.Application
import com.example.newsapplication.dagger.DaggerAppComponent

class NewsApplication: Application() {
    var appComponent = DaggerAppComponent.create()
}