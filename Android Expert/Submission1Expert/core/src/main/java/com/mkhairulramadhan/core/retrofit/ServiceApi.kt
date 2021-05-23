package com.mkhairulramadhan.core.retrofit

import com.mkhairulramadhan.core.data.remote.MovieDataItem
import com.mkhairulramadhan.core.data.remote.MovieTvResponse
import com.mkhairulramadhan.core.data.remote.TvDataItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {
    @GET("movie/popular")
    fun getAllMovie(@Query("api_key") apiKey: String?) : MovieTvResponse<MovieDataItem>

    @GET("tv/popular")
    fun getAllTv(@Query("api_key") apiKey: String?) : MovieTvResponse<TvDataItem>

    @GET("movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") movieId: Int?, @Query("api_key") apiKey: String?) : MovieDataItem

    @GET("tv/{tv_id}")
    fun getDetailTv(@Path("tv_id") seriesId: Int?, @Query("api_key") apiKey: String?) : TvDataItem
}