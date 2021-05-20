package com.mkhairulramadhan.submission1moviecatalog.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.MovieEntity
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.TvEntity

@Dao
interface GopoxDao {

    //movie
    @Query("SELECT * FROM movieEntity")
    fun getAllMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieEntity WHERE id = :movieId")
    fun getMovieById(movieId: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailMovie(movie: MovieEntity)

    @Query("SELECT * FROM movieEntity where favorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Update
    fun updateMovie(movie: MovieEntity)



    //Tv
    @Query("SELECT * FROM tvEntity")
    fun getAllTv(): DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM tvEntity WHERE id = :tvId")
    fun getTvById(tvId: Int): LiveData<TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tv: List<TvEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailTv(tv: TvEntity)

    @Query("SELECT * FROM tvEntity where favorite = 1")
    fun getFavoriteTv(): DataSource.Factory<Int, TvEntity>

    @Update
    fun updateTv(tv: TvEntity)

}