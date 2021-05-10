package com.mkhairulramadhan.submission1moviecatalog.di

import com.mkhairulramadhan.submission1moviecatalog.data.GopoxMovieRepository
import com.mkhairulramadhan.submission1moviecatalog.data.remote.RemoteDataSource
import com.mkhairulramadhan.submission1moviecatalog.retrofit.ConfigApi

object Injection {

    fun injectRepository(): GopoxMovieRepository{
        val remoteSource = RemoteDataSource.getInstance(ConfigApi.make())
        return GopoxMovieRepository.getInstance(remoteSource)
    }

}