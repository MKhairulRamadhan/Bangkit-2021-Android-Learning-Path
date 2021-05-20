package com.mkhairulramadhan.submission1moviecatalog.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    fun getAllMovie(): LiveData<ApiResponse<List<MovieDataItem>>>{
        IdlingResource.increment()
        val dataMovie = MutableLiveData<ApiResponse<List<MovieDataItem>>>()
        apiRequest.getAllMovie(BuildConfig.API_TOKEN).enqueue(object :
            Callback<MovieTvResponse<MovieDataItem>> {
            override fun onResponse(
                call: Call<MovieTvResponse<MovieDataItem>>,
                response: Response<MovieTvResponse<MovieDataItem>>
            ) {
                dataMovie.value = response.body()?.let { ApiResponse.success(it.results) }
                    IdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieTvResponse<MovieDataItem>>, t: Throwable) {
                Log.d("request", t.toString())
                IdlingResource.decrement()
            }

        })
        return dataMovie
    }

    fun getDetailMovie(id: Int): LiveData<ApiResponse<MovieDataItem>>{
        IdlingResource.increment()
        val dataMovie = MutableLiveData<ApiResponse<MovieDataItem>>()
        apiRequest.getDetailMovie(id, BuildConfig.API_TOKEN).enqueue(object : Callback<MovieDataItem>{
            override fun onResponse(call: Call<MovieDataItem>, response: Response<MovieDataItem>) {
                dataMovie.value = response.body()?.let { ApiResponse.success(it) }
                IdlingResource.decrement()
            }
            override fun onFailure(call: Call<MovieDataItem>, t: Throwable) {
                Log.d("request",t.toString())
                IdlingResource.decrement()
            }
        })
        return dataMovie
    }

    fun getAllTv(): LiveData<ApiResponse<List<TvDataItem>>>{
        IdlingResource.increment()
        val dataTv = MutableLiveData<ApiResponse<List<TvDataItem>>>()
        apiRequest.getAllTv(BuildConfig.API_TOKEN).enqueue(object : Callback<MovieTvResponse<TvDataItem>>{
            override fun onResponse(call: Call<MovieTvResponse<TvDataItem>>, response: Response<MovieTvResponse<TvDataItem>>) {
                dataTv.value = response.body()?.let { ApiResponse.success(it.results) }
                IdlingResource.decrement()
            }
            override fun onFailure(call: Call<MovieTvResponse<TvDataItem>>, t: Throwable) {
                Log.d("request",t.toString())
                IdlingResource.decrement()
            }
        })
        return dataTv
    }

    fun getDetailTv(id:Int): LiveData<ApiResponse<TvDataItem>>{
        IdlingResource.increment()
        val dataTv = MutableLiveData<ApiResponse<TvDataItem>>()
        apiRequest.getDetailTv(id, BuildConfig.API_TOKEN).enqueue(object : Callback<TvDataItem>{
            override fun onResponse(call: Call<TvDataItem>, response: Response<TvDataItem>) {
                dataTv.value = response.body()?.let { ApiResponse.success(it) }
                IdlingResource.decrement()
            }
            override fun onFailure(call: Call<TvDataItem>, t: Throwable) {
                Log.d("request",t.toString())
                IdlingResource.decrement()
            }
        })
        return dataTv
    }

}