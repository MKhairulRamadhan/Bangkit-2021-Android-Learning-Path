package com.mkhairulramadhan.submission1moviecatalog.utils

import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResource {
    private const val RESOURCE: String = "GLOBAL"
    val idlingTestResource = CountingIdlingResource(RESOURCE)
    fun increment() = idlingTestResource.increment()
    fun decrement() = idlingTestResource.decrement()
}