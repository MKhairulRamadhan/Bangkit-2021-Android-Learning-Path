package com.mkhairulramadhan.core.domain.repository

import com.mkhairulramadhan.core.domain.model.MovieModel
import com.mkhairulramadhan.core.domain.model.TvModel
import com.mkhairulramadhan.core.valueObject.ResourceData
import kotlinx.coroutines.flow.Flow

interface IGopoxMovieRepository {
    fun getAllMovie(): Flow<ResourceData<List<MovieModel>>>
    fun getDetailMovie(id: Int): Flow<ResourceData<MovieModel>>
    fun setMovieFavorite(movie: MovieModel, state: Boolean)
    fun getFavoriteMovie(): Flow<List<MovieModel>>
    fun getAllTv(): Flow<ResourceData<List<TvModel>>>
    fun getDetailTv(id: Int): Flow<ResourceData<TvModel>>
    fun setTvFavorite(tv: TvModel, state: Boolean)
    fun getFavoriteTv(): Flow<List<TvModel>>
}