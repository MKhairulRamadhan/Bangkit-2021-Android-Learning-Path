package com.mkhairulramadhan.submission1moviecatalog.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConfigApi {

    private val httpClient = OkHttpClient.Builder().build()

        fun make(): ServiceApi {
            val retrofit = Retrofit.Builder()
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.themoviedb.org/3/")
                    .build()
            return retrofit.create(ServiceApi::class.java)
        }


}