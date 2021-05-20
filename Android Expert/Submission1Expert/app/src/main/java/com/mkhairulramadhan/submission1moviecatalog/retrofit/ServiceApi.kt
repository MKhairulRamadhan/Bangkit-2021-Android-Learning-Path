package com.mkhairulramadhan.submission1moviecatalog.retrofit

import com.mkhairulramadhan.submission1moviecatalog.data.remote.MovieDataItem
import com.mkhairulramadhan.submission1moviecatalog.data.remote.MovieTvResponse
import com.mkhairulramadhan.submission1moviecatalog.data.remote.TvDataItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {
    @GET("movie/popular")
    fun getAllMovie(@Query("api_key") apiKey: String?) : Call<MovieTvResponse<MovieDataItem>>

    @GET("tv/popular")
    fun getAllTv(@Query("api_key") apiKey: String?) : Call<MovieTvResponse<TvDataItem>>

    @GET("movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") movieId: Int?, @Query("api_key") apiKey: String?) : Call<MovieDataItem>

    @GET("tv/{tv_id}")
    fun getDetailTv(@Path("tv_id") seriesId: Int?, @Query("api_key") apiKey: String?) : Call<TvDataItem>
}