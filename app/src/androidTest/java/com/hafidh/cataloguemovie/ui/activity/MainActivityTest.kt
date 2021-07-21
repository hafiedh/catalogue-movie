package com.hafidh.cataloguemovie.ui.activity

import  androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.hafidh.cataloguemovie.R
import com.hafidh.cataloguemovie.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

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
        onView(withId(R.id.nav_tv)).perform(click())
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
        onView(withId(R.id.title)).check(matches(isDisplayed()))
        onView(withId(R.id.imgBig)).check(matches(isDisplayed()))
        onView(withId(R.id.imgSmall)).check(matches(isDisplayed()))
        onView(withId(R.id.clDetail)).perform(swipeUp())
        onView(withId(R.id.collapseToolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.release)).check(matches(isDisplayed()))
        onView(withId(R.id.rating)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvDetail() {
        onView(withId(R.id.nav_tv)).perform(click())
        onView(withId(R.id.rvTvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.title)).check(matches(isDisplayed()))
        onView(withId(R.id.imgBig)).check(matches(isDisplayed()))
        onView(withId(R.id.imgSmall)).check(matches(isDisplayed()))
        onView(withId(R.id.clDetail)).perform(swipeUp())
        onView(withId(R.id.collapseToolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.release)).check(matches(isDisplayed()))
        onView(withId(R.id.rating)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavorite() {
        onView(withId(R.id.nav_fav)).perform(click())
        onView(withId(R.id.rvMovieFav)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovieFav)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                19,
            )
        )
    }

    @Test
    fun loadFavoriteTv() {
        onView(withId(R.id.nav_fav)).perform(click())
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rvTvFav)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvFav)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                19,
            )
        )
    }

    @Test
    fun loadDetailFavMovie() {
        onView(withId(R.id.nav_movie)).perform(click())
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.favorite)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.nav_fav)).perform(click())
        onView(withId(R.id.rvMovieFav)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.title)).check(matches(isDisplayed()))
        onView(withId(R.id.imgBig)).check(matches(isDisplayed()))
        onView(withId(R.id.imgSmall)).check(matches(isDisplayed()))
        onView(withId(R.id.clDetail)).perform(swipeUp())
        onView(withId(R.id.collapseToolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.release)).check(matches(isDisplayed()))
        onView(withId(R.id.rating)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(matches(isDisplayed()))

    }

    @Test
    fun loadDetailTvFav() {
        onView(withId(R.id.nav_tv)).perform(click())
        onView(withId(R.id.rvTvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.favorite)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.nav_fav)).perform(click())
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rvTvFav)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvFav)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.title)).check(matches(isDisplayed()))
        onView(withId(R.id.imgBig)).check(matches(isDisplayed()))
        onView(withId(R.id.imgSmall)).check(matches(isDisplayed()))
        onView(withId(R.id.clDetail)).perform(swipeUp())
        onView(withId(R.id.collapseToolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.release)).check(matches(isDisplayed()))
        onView(withId(R.id.rating)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
    }

}