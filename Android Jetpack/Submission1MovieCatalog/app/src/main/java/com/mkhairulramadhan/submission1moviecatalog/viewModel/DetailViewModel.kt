package com.mkhairulramadhan.submission1moviecatalog.viewModel

import androidx.lifecycle.ViewModel
import com.mkhairulramadhan.submission1moviecatalog.model.MovieTvModel
import com.mkhairulramadhan.submission1moviecatalog.utils.DataDummy

class DetailViewModel: ViewModel() {

    private lateinit var movieId: String
    private lateinit var tvId: String

    private fun getMovieData(): ArrayList<MovieTvModel> = DataDummy.generateDataMovie()

    private fun getTvData(): ArrayList<MovieTvModel> = DataDummy.generateDataTv()

    fun setMovieId(movieId: String){
        this.movieId = movieId
    }

    fun setTvId(tvId: String){
        this.tvId = tvId
    }

    fun getDetailMovie(): MovieTvModel{
        lateinit var data: MovieTvModel
        val listMovie = getMovieData()
        for (movie in listMovie){
            if (movie.id == movieId){
                data = movie
                break
            }
        }
        return data
    }

    fun getDetailTv(): MovieTvModel{
        lateinit var data: MovieTvModel
        val listTv = getTvData()
        for (tv in listTv){
            if (tv.id == tvId){
                data = tv
                break
            }
        }
        return data
    }

}