package com.mkhairulramadhan.submission1moviecatalog.data

import androidx.lifecycle.LiveData
import com.mkhairulramadhan.submission1moviecatalog.data.remote.MovieDataItem
import com.mkhairulramadhan.submission1moviecatalog.data.remote.TvDataItem
import com.mkhairulramadhan.submission1moviecatalog.model.MovieTvModel

interface GopoxMovieDataSource {
    fun getAllMovie(): LiveData<List<MovieTvModel>>
    fun getDetailMovie(id: Int): LiveData<MovieTvModel>
    fun getAllTv(): LiveData<List<MovieTvModel>>
    fun getDetailTv(id: Int): LiveData<MovieTvModel>
}