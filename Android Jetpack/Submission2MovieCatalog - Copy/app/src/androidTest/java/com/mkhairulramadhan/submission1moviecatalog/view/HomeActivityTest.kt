package com.mkhairulramadhan.submission1moviecatalog.view

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.mkhairulramadhan.submission1moviecatalog.R
import com.mkhairulramadhan.submission1moviecatalog.utils.DataDummy
import com.mkhairulramadhan.submission1moviecatalog.utils.IdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest{
    private val dummyMovie = DataDummy.generateDataMovie()
    private val dummyTv = DataDummy.generateDataTv()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(IdlingResource.idlingTestResource)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(IdlingResource.idlingTestResource)
    }

    @Test
    fun loadMovies(){
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.star_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.genre_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.year_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.duration_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.language_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.synopsis_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.poster_image_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.backdrop_image_detail)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvs(){
        onView(withId(R.id.nav_tv)).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTv.size))
    }

    @Test
    fun loadDetailTv() {
        onView(withId(R.id.nav_tv)).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.star_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.genre_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.year_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.duration_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.language_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.synopsis_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.poster_image_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.backdrop_image_detail)).check(matches(isDisplayed()))
    }

}