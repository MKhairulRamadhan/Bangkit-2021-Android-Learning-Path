package com.mkhairulramadhan.submission1moviecatalog.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mkhairulramadhan.submission1moviecatalog.data.GopoxMovieRepository
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.MovieEntity
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.TvEntity
import com.mkhairulramadhan.submission1moviecatalog.valueObject.ResourceData

class MovieTvViewModel(private val repository: GopoxMovieRepository): ViewModel() {

    fun getMovieData(): LiveData<ResourceData<PagedList<MovieEntity>>> = repository.getAllMovie()
    fun getTvData(): LiveData<ResourceData<PagedList<TvEntity>>> = repository.getAllTv()

}