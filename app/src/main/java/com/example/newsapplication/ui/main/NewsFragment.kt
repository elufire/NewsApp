package com.example.newsapplication.ui.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.NewsApplication
import com.example.newsapplication.databinding.FragmentNewsBinding
import javax.inject.Inject

class NewsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: NewsViewModel
    companion object {
        fun newInstance() = NewsFragment()

        fun newInstance(args: Bundle): NewsFragment {
            val newsFragment = NewsFragment()
            newsFragment.arguments = args
            return newsFragment
        }

        fun createArgs(query: String?) = Bundle().apply {
            putString("QUERY", query)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("Jose", arguments?.getString("QUERY"))
        (context?.applicationContext as NewsApplication).appComponent.inject(this)
        val binding = FragmentNewsBinding.inflate(inflater)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)
        binding.viewModel = viewModel
        val searchQuery = arguments?.getString("QUERY")
        if(!searchQuery.isNullOrEmpty()) {
            onSearchEvent(searchQuery)
        } else {
            onTopHeadlinesEvent()
        }
        return binding.root
    }

    private fun onSearchEvent(query: String) {
        viewModel.searchArticles(query)
    }

    fun onTopHeadlinesEvent() {
        viewModel.getTopHeadlines()
    }
}
