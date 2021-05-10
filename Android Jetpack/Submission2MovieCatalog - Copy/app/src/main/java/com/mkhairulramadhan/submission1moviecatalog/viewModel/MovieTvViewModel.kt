package com.mkhairulramadhan.submission1moviecatalog.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mkhairulramadhan.submission1moviecatalog.data.GopoxMovieRepository
import com.mkhairulramadhan.submission1moviecatalog.model.MovieTvModel

class MovieTvViewModel(private val repository: GopoxMovieRepository): ViewModel() {

    val getMovieData: LiveData<List<MovieTvModel>> = repository.getAllMovie()
    val getTvData: LiveData<List<MovieTvModel>> = repository.getAllTv()

}