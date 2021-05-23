package com.mkhairulramadhan.core.data.local.room

import androidx.room.*
import com.mkhairulramadhan.core.data.local.entity.MovieEntity
import com.mkhairulramadhan.core.data.local.entity.TvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GopoxDao {

    //movie
    @Query("SELECT * FROM movieEntity")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieEntity WHERE id = :movieId")
    fun getMovieById(movieId: Int): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailMovie(movie: MovieEntity)

    @Query("SELECT * FROM movieEntity where favorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Update
    fun updateMovie(movie: MovieEntity)

    //Tv
    @Query("SELECT * FROM tvEntity")
    fun getAllTv(): Flow<List<TvEntity>>

    @Query("SELECT * FROM tvEntity WHERE id = :tvId")
    fun getTvById(tvId: Int): Flow<TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTv(tv: List<TvEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailTv(tv: TvEntity)

    @Query("SELECT * FROM tvEntity where favorite = 1")
    fun getFavoriteTv(): Flow<List<TvEntity>>

    @Update
    fun updateTv(tv: TvEntity)

}