package com.example.newsapplication

import com.example.newsapplication.models.NewsTypes
import com.example.newsapplication.ui.main.ArticleViewModel
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

const val TITLE = "Momentum builds for NYC teacher 'sickout' mutiny over" +
        " de Blasio's refusal to close schools - New York Post "
const val AUTHOR = "Susan Edelman, Melissa Klein"
const val IMAGE_URL = "https://thenypost.files.wordpress.com/2020/03/" +
        "michael-mulgrew-bill-de-blasio-sickout.jpg?quality=90&strip=all&w=1200"

class ArticleViewModelTest {
    lateinit var articleViewModel: ArticleViewModel

    @Before
    fun setUp() {
        val article = mockk<NewsTypes.Article> {
            every { title } returns TITLE
            every { author } returns AUTHOR
            every { urlToImage } returns IMAGE_URL
        }
        articleViewModel = ArticleViewModel(article)
    }

    @Test
    fun `given valid article then set up article viewModel`() {
        assertEquals(TITLE, articleViewModel.title.get())
        assertEquals(AUTHOR, articleViewModel.author.get())
        assertEquals(IMAGE_URL, articleViewModel.imageUrl.get())
    }
}