package com.mkhairulramadhan.submission1moviecatalog.view

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.mkhairulramadhan.submission1moviecatalog.R
import com.mkhairulramadhan.submission1moviecatalog.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest{
    private val dummyMovie = DataDummy.generateDataMovie()
    private val dummyTv = DataDummy.generateDataTv()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

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
        onView(withId(R.id.title_detail)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.director_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.director_detail)).check(matches(withText(dummyMovie[0].director)))
        onView(withId(R.id.star_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.star_detail)).check(matches(withText(dummyMovie[0].star)))
        onView(withId(R.id.genre_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.genre_detail)).check(matches(withText(dummyMovie[0].tag)))
        onView(withId(R.id.year_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.year_detail)).check(matches(withText(dummyMovie[0].year)))
        onView(withId(R.id.duration_detial)).check(matches(isDisplayed()))
        onView(withId(R.id.duration_detial)).check(matches(withText(dummyMovie[0].duration)))
        onView(withId(R.id.age_detial)).check(matches(isDisplayed()))
        onView(withId(R.id.age_detial)).check(matches(withText(dummyMovie[0].age)))
        onView(withId(R.id.synopsis_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.synopsis_detail)).check(matches(withText(dummyMovie[0].synopsis)))
        onView(withId(R.id.image_detail)).check(matches(isDisplayed()))
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
        onView(withId(R.id.title_detail)).check(matches(withText(dummyTv[0].title)))
        onView(withId(R.id.director_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.director_detail)).check(matches(withText(dummyTv[0].director)))
        onView(withId(R.id.star_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.star_detail)).check(matches(withText(dummyTv[0].star)))
        onView(withId(R.id.genre_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.genre_detail)).check(matches(withText(dummyTv[0].tag)))
        onView(withId(R.id.year_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.year_detail)).check(matches(withText(dummyTv[0].year)))
        onView(withId(R.id.duration_detial)).check(matches(isDisplayed()))
        onView(withId(R.id.duration_detial)).check(matches(withText(dummyTv[0].duration)))
        onView(withId(R.id.age_detial)).check(matches(isDisplayed()))
        onView(withId(R.id.age_detial)).check(matches(withText(dummyTv[0].age)))
        onView(withId(R.id.synopsis_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.synopsis_detail)).check(matches(withText(dummyTv[0].synopsis)))
        onView(withId(R.id.image_detail)).check(matches(isDisplayed()))
    }

}