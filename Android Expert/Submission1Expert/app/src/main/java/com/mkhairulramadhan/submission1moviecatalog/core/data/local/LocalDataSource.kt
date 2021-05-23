package com.mkhairulramadhan.submission1moviecatalog.core.data.local

import androidx.lifecycle.LiveData
import com.mkhairulramadhan.submission1moviecatalog.core.data.local.entity.MovieEntity
import com.mkhairulramadhan.submission1moviecatalog.core.data.local.entity.TvEntity
import com.mkhairulramadhan.submission1moviecatalog.core.data.local.room.GopoxDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val gopoxDao: GopoxDao){

    companion object{
        private var INSTANCE: LocalDataSource? = null
        fun getInstance(gopoxDao: GopoxDao): LocalDataSource {
            if (INSTANCE == null){
                INSTANCE = LocalDataSource(gopoxDao)
            }
            return INSTANCE as LocalDataSource
        }
    }

    fun getAllMovie(): Flow<List<MovieEntity>> = gopoxDao.getAllMovie()

    fun getAllTv(): Flow<List<TvEntity>> = gopoxDao.getAllTv()

    fun getDetailMovie(movieId: Int): Flow<MovieEntity> = gopoxDao.getMovieById(movieId)

    fun getDetailTv(tvId: Int): Flow<TvEntity> = gopoxDao.getTvById(tvId)

    suspend fun insertMovie(movie: List<MovieEntity>) = gopoxDao.insertMovie(movie)

    suspend fun insertTv(tv: List<TvEntity>) = gopoxDao.insertTv(tv)

    suspend fun insertDetailMovie(movie: MovieEntity) = gopoxDao.insertDetailMovie(movie)

    suspend fun insertDetailTv(tv: TvEntity) = gopoxDao.insertDetailTv(tv)

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = gopoxDao.getFavoriteMovie()

    fun getFavoriteTv(): Flow<List<TvEntity>> = gopoxDao.getFavoriteTv()

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        movie.favorite = state
        gopoxDao.updateMovie(movie)
    }

    fun setFavoriteTv(tv: TvEntity, state: Boolean) {
        tv.favorite = state
        gopoxDao.updateTv(tv)
    }

}