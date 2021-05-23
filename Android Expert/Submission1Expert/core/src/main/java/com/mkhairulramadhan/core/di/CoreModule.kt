package com.mkhairulramadhan.core.di

import androidx.room.Room
import com.mkhairulramadhan.core.BuildConfig
import com.mkhairulramadhan.core.data.GopoxMovieRepository
import com.mkhairulramadhan.core.data.local.LocalDataSource
import com.mkhairulramadhan.core.data.local.room.GopoxDatabase
import com.mkhairulramadhan.core.data.remote.RemoteDataSource
import com.mkhairulramadhan.core.domain.repository.IGopoxMovieRepository
import com.mkhairulramadhan.core.retrofit.ServiceApi
import com.mkhairulramadhan.core.utils.ExecutorApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<GopoxDatabase>().gopoxDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GopoxDatabase::class.java, "GopoxMovie.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ServiceApi::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { ExecutorApp() }
    single<IGopoxMovieRepository> { GopoxMovieRepository(get(), get(), get()) }
}