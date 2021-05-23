package com.mkhairulramadhan.submission1moviecatalog.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mkhairulramadhan.submission1moviecatalog.core.domain.model.MovieModel
import com.mkhairulramadhan.submission1moviecatalog.core.domain.model.TvModel
import com.mkhairulramadhan.submission1moviecatalog.core.domain.usecase.GopoxMovieUseCase
import com.mkhairulramadhan.submission1moviecatalog.core.valueObject.ResourceData

class MovieTvViewModel(gopoxMovieUseCase: GopoxMovieUseCase): ViewModel() {

    val getMovieData: LiveData<ResourceData<List<MovieModel>>> = gopoxMovieUseCase.getAllMovie().asLiveData()
    val getTvData: LiveData<ResourceData<List<TvModel>>> = gopoxMovieUseCase.getAllTv().asLiveData()

}