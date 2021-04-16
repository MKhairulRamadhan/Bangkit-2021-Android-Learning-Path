package com.mkhairulramadhan.consumerapp.db.network

import com.mkhairulramadhan.consumerapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfig {

    private val client by lazy {
        OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                            .header("Authorization", BuildConfig.ACCESS_TOKEN)
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build()
    }

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
    }

    val apiUserClient: UserNetwork by lazy {
     retrofitBuilder.build().create(UserNetwork::class.java)
    }




}