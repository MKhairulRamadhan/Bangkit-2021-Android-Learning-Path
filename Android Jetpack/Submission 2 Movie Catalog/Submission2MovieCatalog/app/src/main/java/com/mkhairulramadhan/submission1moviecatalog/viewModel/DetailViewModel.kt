package com.mkhairulramadhan.submission1moviecatalog.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mkhairulramadhan.submission1moviecatalog.data.GopoxMovieRepository
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.MovieEntity
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.TvEntity
import com.mkhairulramadhan.submission1moviecatalog.valueObject.ResourceData

class DetailViewModel(private val repository: GopoxMovieRepository): ViewModel() {

    private val idMovie = MutableLiveData<Int>()
    private val idTv = MutableLiveData<Int>()

    fun setSelectMovie(id: Int){
        this.idMovie.value = id
    }

    fun setSelectTv(id: Int){
        this.idTv.value = id
    }

    var allMovie: LiveData<ResourceData<MovieEntity>> =
        Transformations.switchMap(idMovie){
            repository.getDetailMovie(it)
        }

    var allTv: LiveData<ResourceData<TvEntity>> =
        Transformations.switchMap(idTv){
            repository.getDetailTv(it)
        }

    fun setMovieFavorire(){
        val movieData = allMovie.value
        if (movieData != null){
            val entity = movieData.data
            if (entity!=null){
                val state = !entity.favorite
                repository.setMovieFavorite(entity, state)
            }
        }
    }

    fun setTvFavorire(){
        val tvData = allTv.value
        if (tvData != null){
            val entity = tvData.data
            if (entity!=null){
                val state = !entity.favorite
                repository.setTvFavorite(entity, state)
            }
        }
    }

}