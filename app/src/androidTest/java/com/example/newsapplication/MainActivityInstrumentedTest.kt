package com.example.newsapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.newsapplication.models.NewsTypes
import com.example.newsapplication.network.NewsApi
import com.example.newsapplication.ui.main.ArticleRecyclerViewAdapter
import com.google.gson.Gson
import io.mockk.MockKAnnotations
import io.mockk.every
import io.reactivex.Observable
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

const val BELOW_THE_FOLD_ITEM = 18

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    @get:Rule
    val testRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java, false, false)

    lateinit var testComponent: TestAppComponent
    lateinit var app: NewsApplication
    lateinit var news: NewsTypes.NewsResponse

    @Inject
    lateinit var newsApi: NewsApi

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as NewsApplication
        testComponent = DaggerTestAppComponent.builder()
            .networkModule(TestModule())
            .build()
        app.appComponent = testComponent
        testComponent.inject(this)
        news = Gson().fromJson<NewsTypes.NewsResponse>(getJsonFromAssets(InstrumentationRegistry.getInstrumentation().context.assets.open("NewsResponse.json")), NewsTypes.NewsResponse::class.java)
        every { newsApi.getTopHeadlines() } returns Observable.just(news)
    }

    @Test
    fun testInitialization() {
        testRule.launchActivity(null)
        assertNotNull(newsApi)
        assertNotNull(app)
        onView(withId(R.id.title)).check(matches(withText("Attention Here's The News")))
    }

    @Test
    fun testFirstViewInRecycler() {
        testRule.launchActivity(null)
        onView(withText("Shutdowns Spread Across Europe as Spain, France Order Broad" +
                " Restrictions - The New York Times")).check(matches(isDisplayed()))
        onView(withText("By\nAdam Nossiter, Raphael Minder, Elian Peltier"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testBelowTheFoldViewInRecycler() {
        testRule.launchActivity(null)
        onView(withId(R.id.articlesRv))
            .perform(
                scrollToPosition<ArticleRecyclerViewAdapter.ViewHolder>(
                    BELOW_THE_FOLD_ITEM
                )
            )
        onView(withText("Fresh rocket attack wounds international troops in Iraq," +
                " potentially deepening U.S.-Iran tensions - The Washington Post"))
            .check(matches(isDisplayed()))
        onView(withText("By\nMissy Ryan, Louisa Loveluck, Mustafa Salim"))
            .check(matches(isDisplayed()))
    }

    fun getJsonFromAssets(inputStream: InputStream): String? {
        val jsonString: String
        jsonString = try {
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }
}