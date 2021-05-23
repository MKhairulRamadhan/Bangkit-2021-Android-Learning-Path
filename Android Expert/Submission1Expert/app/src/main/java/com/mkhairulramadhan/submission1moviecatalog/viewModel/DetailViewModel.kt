package com.mkhairulramadhan.submission1moviecatalog.viewModel

import androidx.lifecycle.*
import com.mkhairulramadhan.core.domain.model.MovieModel
import com.mkhairulramadhan.core.domain.model.TvModel
import com.mkhairulramadhan.core.domain.usecase.GopoxMovieUseCase
import com.mkhairulramadhan.core.valueObject.ResourceData

class DetailViewModel(private val gopoxMovieUseCase: GopoxMovieUseCase): ViewModel() {

    private val idMovie = MutableLiveData<Int>()
    private val idTv = MutableLiveData<Int>()

    fun setSelectMovie(id: Int){
        this.idMovie.value = id
    }

    fun setSelectTv(id: Int){
        this.idTv.value = id
    }

    var allMovie: LiveData<ResourceData<MovieModel>> =
        Transformations.switchMap(idMovie){
            gopoxMovieUseCase.getDetailMovie(it).asLiveData()
        }

    var allTv: LiveData<ResourceData<TvModel>> =
        Transformations.switchMap(idTv){
            gopoxMovieUseCase.getDetailTv(it).asLiveData()
        }

    fun setMovieFavorire(){
        val movieData = allMovie.value
        if (movieData != null){
            val entity = movieData.data
            if (entity!=null){
                val state = !entity.favorite
                gopoxMovieUseCase.setMovieFavorite(entity, state)
            }
        }
    }

    fun setTvFavorire(){
        val tvData = allTv.value
        if (tvData != null){
            val entity = tvData.data
            if (entity!=null){
                val state = !entity.favorite
                gopoxMovieUseCase.setTvFavorite(entity, state)
            }
        }
    }

}