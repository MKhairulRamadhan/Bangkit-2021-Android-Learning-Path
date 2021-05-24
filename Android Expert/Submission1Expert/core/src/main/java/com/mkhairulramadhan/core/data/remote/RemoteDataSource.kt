package com.mkhairulramadhan.core.data.remote

import android.util.Log
import com.mkhairulramadhan.core.BuildConfig
import com.mkhairulramadhan.core.retrofit.ServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiRequest: ServiceApi) {

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
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailMovie(id: Int): Flow<ApiResponse<MovieDataItem>>{
        return flow {
            try {
                val movieData = apiRequest.getDetailMovie(id, BuildConfig.API_TOKEN)
                emit(ApiResponse.Success(movieData))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
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
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailTv(id:Int): Flow<ApiResponse<TvDataItem>>{
        return flow {
            try {
                val tvData = apiRequest.getDetailTv(id, BuildConfig.API_TOKEN)
                emit(ApiResponse.Success(tvData))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}