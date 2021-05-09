package com.mkhairulramadhan.submission1moviecatalog.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mkhairulramadhan.submission1moviecatalog.data.GopoxMovieRepository
import com.mkhairulramadhan.submission1moviecatalog.data.remote.MovieDataItem
import com.mkhairulramadhan.submission1moviecatalog.data.remote.TvDataItem
import com.mkhairulramadhan.submission1moviecatalog.model.MovieTvModel

class DetailViewModel(private val repository: GopoxMovieRepository): ViewModel() {

    fun getDetailMovie(id: Int): LiveData<MovieTvModel> = repository.getDetailMovie(id)
    fun getDetailTv(id: Int): LiveData<MovieTvModel> = repository.getDetailTv(id)
//    fun getIsLoading(): LiveData<Boolean> = repository.checkLoading()

}