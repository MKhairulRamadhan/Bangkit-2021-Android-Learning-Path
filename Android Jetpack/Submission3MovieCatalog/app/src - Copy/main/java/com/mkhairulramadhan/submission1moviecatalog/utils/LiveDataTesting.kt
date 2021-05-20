package com.mkhairulramadhan.submission1moviecatalog.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

object LiveDataTesting {

    @Suppress("UNCHECKED_CAST")
    fun <T> getValue(liveData: LiveData<T>): T {
        val dataLive = arrayOfNulls<Any>(1)
        val countLatch = CountDownLatch(1)

        val observer = object : Observer<T> {
            override fun onChanged(o: T) {
                dataLive[0] = o
                countLatch.countDown()
                liveData.removeObserver(this)
            }
        }
        liveData.observeForever(observer)
        try {
            countLatch.await(2, TimeUnit.SECONDS)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return dataLive[0] as T

    }
}