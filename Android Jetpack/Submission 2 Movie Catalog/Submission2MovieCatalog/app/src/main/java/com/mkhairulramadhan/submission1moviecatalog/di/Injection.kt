package com.mkhairulramadhan.submission1moviecatalog.di

import android.content.Context
import com.mkhairulramadhan.submission1moviecatalog.data.GopoxMovieRepository
import com.mkhairulramadhan.submission1moviecatalog.data.local.LocalDataSource
import com.mkhairulramadhan.submission1moviecatalog.data.local.room.GopoxDatabase
import com.mkhairulramadhan.submission1moviecatalog.data.remote.RemoteDataSource
import com.mkhairulramadhan.submission1moviecatalog.retrofit.ConfigApi
import com.mkhairulramadhan.submission1moviecatalog.utils.ExecutorApp

object Injection {

    fun injectRepository(context: Context): GopoxMovieRepository{
        val remoteSource = RemoteDataSource.getInstance(ConfigApi.make())
        val db = GopoxDatabase.getInstance(context)
        val localSource = LocalDataSource.getInstance(db.gopoxDao())
        val executorApp = ExecutorApp()
        return GopoxMovieRepository.getInstance(remoteSource, localSource, executorApp)
    }

}