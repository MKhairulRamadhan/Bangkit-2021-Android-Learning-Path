package com.mkhairulramadhan.submission1moviecatalog.viewModel

import androidx.lifecycle.ViewModel
import com.mkhairulramadhan.submission1moviecatalog.model.MovieTvModel

class DetailViewModel: ViewModel() {

    lateinit var listDetailMovieTv: MovieTvModel

    fun setDetailMovieTv(data: MovieTvModel){
        listDetailMovieTv = data
    }
    
}