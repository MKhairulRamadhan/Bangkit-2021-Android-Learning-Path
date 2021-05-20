package com.mkhairulramadhan.submission1moviecatalog.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.MovieEntity
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.TvEntity
import com.mkhairulramadhan.submission1moviecatalog.valueObject.ResourceData

interface GopoxMovieDataSource {
    fun getAllMovie(): LiveData<ResourceData<PagedList<MovieEntity>>>
    fun getDetailMovie(id: Int): LiveData<ResourceData<MovieEntity>>
    fun setMovieFavorite(movie: MovieEntity, state: Boolean)
    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>
    fun getAllTv(): LiveData<ResourceData<PagedList<TvEntity>>>
    fun getDetailTv(id: Int): LiveData<ResourceData<TvEntity>>
    fun setTvFavorite(tv: TvEntity, state: Boolean)
    fun getFavoriteTv(): LiveData<PagedList<TvEntity>>
}