package com.mkhairulramadhan.submission1moviecatalog.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mkhairulramadhan.submission1moviecatalog.data.GopoxMovieRepository
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.MovieEntity
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.TvEntity

class FavoriteViewModel(private val repository: GopoxMovieRepository): ViewModel() {
    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> = repository.getFavoriteMovie()
    fun getFavoriteTv(): LiveData<PagedList<TvEntity>> = repository.getFavoriteTv()

    fun setFavoriteMovie(movieEntity: MovieEntity){
        val state = !movieEntity.favorite
        repository.setMovieFavorite(movieEntity, state)
    }

    fun setFavoriteTv(tvEntity: TvEntity){
        val state = !tvEntity.favorite
        repository.setTvFavorite(tvEntity, state)
    }


}