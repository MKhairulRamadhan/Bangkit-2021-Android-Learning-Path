package com.mkhairulramadhan.submission1moviecatalog.core.data.remote

import android.util.Log
import com.mkhairulramadhan.submission1moviecatalog.BuildConfig
import com.mkhairulramadhan.submission1moviecatalog.core.retrofit.ServiceApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource(private val apiRequest: ServiceApi) {

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(apiRequest: ServiceApi): RemoteDataSource =
                instance ?: synchronized(this){
                    instance ?: RemoteDataSource(apiRequest)
                }
    }

    fun getAllMovie(): Flow<ApiResponse<List<MovieDataItem>>> {
        return flow {
            try {
                val response = apiRequest.getAllMovie(BuildConfig.API_TOKEN)
                val movieArray = response.results
                if (movieArray.isNotEmpty()){
                    emit(ApiResponse.Success(movieArray))
                }else{
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }
    }

    fun getDetailMovie(id: Int): Flow<ApiResponse<MovieDataItem>>{
        return flow {
            try {
                val movieData = apiRequest.getDetailMovie(id, BuildConfig.API_TOKEN)
                emit(ApiResponse.Success(movieData))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }
    }

    fun getAllTv(): Flow<ApiResponse<List<TvDataItem>>>{
        return flow {
            try {
                val response = apiRequest.getAllTv(BuildConfig.API_TOKEN)
                val tvArray = response.results
                if (tvArray.isNotEmpty()){
                    emit(ApiResponse.Success(tvArray))
                }else{
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }
    }

    fun getDetailTv(id:Int): Flow<ApiResponse<TvDataItem>>{
        return flow {
            try {
                val tvData = apiRequest.getDetailTv(id, BuildConfig.API_TOKEN)
                emit(ApiResponse.Success(tvData))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }
    }

}