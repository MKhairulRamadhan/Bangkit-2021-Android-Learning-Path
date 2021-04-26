package com.mkhairulramadhan.submission1moviecatalog.viewModel

import androidx.lifecycle.ViewModel
import com.mkhairulramadhan.submission1moviecatalog.model.MovieTvModel
import com.mkhairulramadhan.submission1moviecatalog.utils.DataDummy

class MovieTvViewModel: ViewModel() {

    fun getMovieData(): ArrayList<MovieTvModel> = DataDummy.generateDataMovie()

    fun getTvData(): ArrayList<MovieTvModel> = DataDummy.generateDataTv()
}