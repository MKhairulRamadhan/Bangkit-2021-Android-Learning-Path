package com.mkhairulramadhan.submission1moviecatalog.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.MovieEntity
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.TvEntity
import com.mkhairulramadhan.submission1moviecatalog.data.local.room.GopoxDao

class LocalDataSource private constructor(private val gopoxDao: GopoxDao){

    companion object{
        private var INSTANCE: LocalDataSource? = null
        fun getInstance(gopoxDao: GopoxDao): LocalDataSource{
            if (INSTANCE == null){
                INSTANCE = LocalDataSource(gopoxDao)
            }
            return INSTANCE as LocalDataSource
        }
    }

    fun getAllMovie(): DataSource.Factory<Int, MovieEntity> = gopoxDao.getAllMovie()

    fun getAllTv(): DataSource.Factory<Int, TvEntity> = gopoxDao.getAllTv()

    fun getMovieById(movieId: Int): LiveData<MovieEntity> = gopoxDao.getMovieById(movieId)

    fun getTvById(tvId: Int): LiveData<TvEntity> = gopoxDao.getTvById(tvId)

    fun insertMovie(movie: List<MovieEntity>) = gopoxDao.insertMovie(movie)

    fun insertTv(tv: List<TvEntity>) = gopoxDao.insertTv(tv)

    fun insertDetailMovie(movie: MovieEntity) = gopoxDao.insertDetailMovie(movie)

    fun insertDetailTv(tv: TvEntity) = gopoxDao.insertDetailTv(tv)

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity> = gopoxDao.getFavoriteMovie()

    fun getFavoriteTv(): DataSource.Factory<Int, TvEntity> = gopoxDao.getFavoriteTv()

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        movie.favorite = state
        gopoxDao.updateMovie(movie)
    }

    fun setFavoriteTv(tv: TvEntity, state: Boolean) {
        tv.favorite = state
        gopoxDao.updateTv(tv)
    }

}