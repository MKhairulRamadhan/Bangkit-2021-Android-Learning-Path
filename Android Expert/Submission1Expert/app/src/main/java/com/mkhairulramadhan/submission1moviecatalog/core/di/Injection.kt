package com.mkhairulramadhan.submission1moviecatalog.core.di

import android.content.Context
import com.mkhairulramadhan.submission1moviecatalog.core.data.GopoxMovieRepository
import com.mkhairulramadhan.submission1moviecatalog.core.data.local.LocalDataSource
import com.mkhairulramadhan.submission1moviecatalog.core.data.local.room.GopoxDatabase
import com.mkhairulramadhan.submission1moviecatalog.core.data.remote.RemoteDataSource
import com.mkhairulramadhan.submission1moviecatalog.core.domain.repository.IGopoxMovieRepository
import com.mkhairulramadhan.submission1moviecatalog.core.domain.usecase.GopoxMovieInteractor
import com.mkhairulramadhan.submission1moviecatalog.core.domain.usecase.GopoxMovieUseCase
import com.mkhairulramadhan.submission1moviecatalog.core.retrofit.ConfigApi
import com.mkhairulramadhan.submission1moviecatalog.core.utils.ExecutorApp

object Injection {

    private fun provideGopoxMovieRepository(context: Context): IGopoxMovieRepository {
        val remoteSource = RemoteDataSource.getInstance(ConfigApi.make())
        val db = GopoxDatabase.getInstance(context)
        val localSource = LocalDataSource.getInstance(db.gopoxDao())
        val executorApp = ExecutorApp()
        return GopoxMovieRepository.getInstance(remoteSource, localSource, executorApp)
    }

    fun provideGopoxMovieUseCase(context: Context): GopoxMovieUseCase{
        val repository = provideGopoxMovieRepository(context)
        return GopoxMovieInteractor(repository)
    }

}