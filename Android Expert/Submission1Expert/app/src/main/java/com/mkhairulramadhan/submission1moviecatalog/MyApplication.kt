package com.mkhairulramadhan.submission1moviecatalog

import android.app.Application
import com.mkhairulramadhan.core.di.databaseModule
import com.mkhairulramadhan.core.di.networkModule
import com.mkhairulramadhan.core.di.repositoryModule
import com.mkhairulramadhan.submission1moviecatalog.di.useCaseModule
import com.mkhairulramadhan.submission1moviecatalog.di.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@FlowPreview
@ExperimentalCoroutinesApi
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