package com.mkhairulramadhan.submission1moviecatalog

import android.app.Application
import com.mkhairulramadhan.submission1moviecatalog.core.di.databaseModule
import com.mkhairulramadhan.submission1moviecatalog.core.di.networkModule
import com.mkhairulramadhan.submission1moviecatalog.core.di.repositoryModule
import com.mkhairulramadhan.submission1moviecatalog.di.useCaseModule
import com.mkhairulramadhan.submission1moviecatalog.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}