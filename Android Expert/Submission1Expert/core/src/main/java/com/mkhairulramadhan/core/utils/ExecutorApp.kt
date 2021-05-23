package com.mkhairulramadhan.core.utils

import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ExecutorApp @VisibleForTesting constructor(
    private val disk: Executor,
    private val network: Executor,
    private val mainThread: Executor
) {
    companion object{
        private const val THREAD = 3
    }

    constructor() : this(
        Executors.newSingleThreadExecutor(),
        Executors.newFixedThreadPool(THREAD),
        MainThreadExecutor()
    )

    fun diskIO(): Executor = disk

    fun networkIO(): Executor = network

    fun mainThread(): Executor = mainThread

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

}