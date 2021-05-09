package com.mkhairulramadhan.submission1moviecatalog.di

import com.mkhairulramadhan.submission1moviecatalog.data.GopoxMovieRepository
import com.mkhairulramadhan.submission1moviecatalog.data.remote.RemoteDataSource

object Injection {

    fun injectRepository(): GopoxMovieRepository{
        val remoteSource = RemoteDataSource.getInstance()
        return GopoxMovieRepository.getInstance(remoteSource)
    }

}