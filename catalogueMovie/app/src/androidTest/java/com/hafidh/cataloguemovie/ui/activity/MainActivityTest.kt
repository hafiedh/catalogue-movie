package com.hafidh.cataloguemovie.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.hafidh.cataloguemovie.R
import com.hafidh.cataloguemovie.utils.DataDummy
import com.hafidh.cataloguemovie.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    private val movieDummy = DataDummy.getDataDummyMovieNetwork().title
    private val detailTv = DataDummy.getDataDummyTvShowDetailNetwork().name

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19)
        )
    }

    @Test
    fun loadTvshows() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rvTvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvshow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19)
        )
    }

    @Test
    fun loadMoviesDetail() {
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.title)).check(matches(isDisplayed())).also {
            it.check(matches(withText(movieDummy)))
        }
    }

    @Test
    fun loadTvDetail() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rvTvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.title)).check(matches(isDisplayed())).also {
            it.check(matches(withText(detailTv)))
        }
    }
}