package com.mkhairulramadhan.submission1moviecatalog.core.retrofit

import com.mkhairulramadhan.submission1moviecatalog.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConfigApi {

    private val httpClient = OkHttpClient.Builder().build()

        fun make(): ServiceApi {
            val retrofit = Retrofit.Builder()
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BuildConfig.BASE_API)
                    .build()
            return retrofit.create(ServiceApi::class.java)
        }

}