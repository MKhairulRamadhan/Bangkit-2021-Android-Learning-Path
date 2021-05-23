package com.mkhairulramadhan.submission1moviecatalog.core.domain.usecase

import com.mkhairulramadhan.submission1moviecatalog.core.domain.model.MovieModel
import com.mkhairulramadhan.submission1moviecatalog.core.domain.model.TvModel
import com.mkhairulramadhan.submission1moviecatalog.core.valueObject.ResourceData
import kotlinx.coroutines.flow.Flow

interface GopoxMovieUseCase {
    fun getAllMovie(): Flow<ResourceData<List<MovieModel>>>
    fun getDetailMovie(id: Int): Flow<ResourceData<MovieModel>>
    fun setMovieFavorite(movie: MovieModel, state: Boolean)
    fun getFavoriteMovie(): Flow<List<MovieModel>>
    fun getAllTv(): Flow<ResourceData<List<TvModel>>>
    fun getDetailTv(id: Int): Flow<ResourceData<TvModel>>
    fun setTvFavorite(tv: TvModel, state: Boolean)
    fun getFavoriteTv(): Flow<List<TvModel>>
}