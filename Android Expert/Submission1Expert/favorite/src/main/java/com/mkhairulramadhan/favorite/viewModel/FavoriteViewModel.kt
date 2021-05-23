package com.mkhairulramadhan.favorite.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mkhairulramadhan.core.domain.model.MovieModel
import com.mkhairulramadhan.core.domain.model.TvModel
import com.mkhairulramadhan.core.domain.usecase.GopoxMovieUseCase

class FavoriteViewModel(private val gopoxMovieUseCase: GopoxMovieUseCase): ViewModel() {
    fun getFavoriteMovie(): LiveData<List<MovieModel>> = gopoxMovieUseCase.getFavoriteMovie().asLiveData()
    fun getFavoriteTv(): LiveData<List<TvModel>> = gopoxMovieUseCase.getFavoriteTv().asLiveData()
}