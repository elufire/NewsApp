package com.example.newsapplication

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.fragment.app.FragmentActivity
import com.example.newsapplication.ui.main.NewsFragment
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        var searchQuery = ""
        if (Intent.ACTION_SEARCH == intent.action) {
            searchQuery = intent.getStringExtra(SearchManager.QUERY)
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NewsFragment.newInstance(NewsFragment.createArgs(searchQuery)), "NewsFrag")
                .commitNow()
        }
        handleNewIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            handleNewIntent(it)
        }

    }

    private fun handleNewIntent(intent: Intent) {
//        if (Intent.ACTION_SEARCH == intent.action) {
//            val searchQuery = intent.getStringExtra(SearchManager.QUERY)
//            val newsFrag = (supportFragmentManager.findFragmentByTag("NewsFrag") as NewsFragment)
//            newsFrag.onSearchEvent(searchQuery)
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.top_headlines) {
            val newsFrag = (supportFragmentManager.findFragmentByTag("NewsFrag") as NewsFragment)
            newsFrag.onTopHeadlinesEvent()
        }
        return super.onOptionsItemSelected(item)
    }

}
