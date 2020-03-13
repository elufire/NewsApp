package com.example.newsapplication.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.newsapplication.NewsApplication
import com.example.newsapplication.R
import com.example.newsapplication.databinding.FragmentNewsBinding
import javax.inject.Inject

class NewsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    companion object {
        fun newInstance() = NewsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (context?.applicationContext as NewsApplication).appComponent.inject(this)
        val binding = FragmentNewsBinding.inflate(inflater)
        binding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
