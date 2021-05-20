package com.mkhairulramadhan.submission1moviecatalog.data.remote

import android.util.Log
import com.mkhairulramadhan.submission1moviecatalog.BuildConfig
import com.mkhairulramadhan.submission1moviecatalog.retrofit.ServiceApi
import com.mkhairulramadhan.submission1moviecatalog.utils.IdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val apiRequest: ServiceApi) {

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(apiRequest: ServiceApi): RemoteDataSource =
                instance ?: synchronized(this){
                    instance?: RemoteDataSource(apiRequest)
                }
    }

    interface CustomGetAllMovieCallback{
        fun onResponse(movieResponse : List<MovieDataItem>)
    }

    interface CustomDetailMovieCallback{
        fun onResponse(movieResponse : MovieDataItem)
    }

    interface CustomGetAllTvCallback{
        fun onResponse(tvResponse : List<TvDataItem>)
    }

    interface CustomDetailTvCallback{
        fun onResponse(tvResponse : TvDataItem)
    }

    fun getAllMovie(getAllMovieCallback: CustomGetAllMovieCallback) {
        IdlingResource.increment()
        apiRequest.getAllMovie(BuildConfig.API_TOKEN)
            .enqueue(object : Callback<MovieTvResponse<MovieDataItem>> {
                override fun onResponse(
                    call: Call<MovieTvResponse<MovieDataItem>>,
                    response: Response<MovieTvResponse<MovieDataItem>>
                ) {
                    response.body()?.let { getAllMovieCallback.onResponse(it.results) }
                    IdlingResource.decrement()
                }
                override fun onFailure(call: Call<MovieTvResponse<MovieDataItem>>, t: Throwable) {
                    Log.d("request", t.toString())
                    IdlingResource.decrement()
                }
            })
    }

    fun getDetailMovie(id: Int, detailMovieCallback: CustomDetailMovieCallback){
        IdlingResource.increment()
        apiRequest.getDetailMovie(id, BuildConfig.API_TOKEN).enqueue(object : Callback<MovieDataItem>{
            override fun onResponse(call: Call<MovieDataItem>, response: Response<MovieDataItem>) {
                response.body()?.let { detailMovieCallback.onResponse(it) }
                IdlingResource.decrement()
            }
            override fun onFailure(call: Call<MovieDataItem>, t: Throwable) {
                Log.d("request",t.toString())
                IdlingResource.decrement()
            }
        })
    }

    fun getAllTv(getAllTvCallback: CustomGetAllTvCallback){
        IdlingResource.increment()
        apiRequest.getAllTv(BuildConfig.API_TOKEN).enqueue(object : Callback<MovieTvResponse<TvDataItem>>{
            override fun onResponse(call: Call<MovieTvResponse<TvDataItem>>, response: Response<MovieTvResponse<TvDataItem>>) {
                response.body()?.let { getAllTvCallback.onResponse(it.results) }
                IdlingResource.decrement()
            }
            override fun onFailure(call: Call<MovieTvResponse<TvDataItem>>, t: Throwable) {
                Log.d("request",t.toString())
                IdlingResource.decrement()
            }

        })
    }

    fun getDetailTv(id:Int, detailTvCallback: CustomDetailTvCallback){
        IdlingResource.increment()
        apiRequest.getDetailTv(id, BuildConfig.API_TOKEN).enqueue(object : Callback<TvDataItem>{
            override fun onResponse(call: Call<TvDataItem>, response: Response<TvDataItem>) {
                response.body()?.let { detailTvCallback.onResponse(it) }
                IdlingResource.decrement()
            }
            override fun onFailure(call: Call<TvDataItem>, t: Throwable) {
                Log.d("request",t.toString())
                IdlingResource.decrement()
            }
        })
    }

}