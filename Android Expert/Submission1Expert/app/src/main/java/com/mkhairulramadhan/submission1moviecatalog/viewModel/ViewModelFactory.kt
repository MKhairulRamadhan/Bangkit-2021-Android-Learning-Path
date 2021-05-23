package com.mkhairulramadhan.submission1moviecatalog.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mkhairulramadhan.submission1moviecatalog.core.di.Injection
import com.mkhairulramadhan.submission1moviecatalog.core.domain.usecase.GopoxMovieUseCase

class ViewModelFactory private constructor(private val usecase: GopoxMovieUseCase) : ViewModelProvider.NewInstanceFactory() {
    companion object{
        @Volatile
        private var instance: ViewModelFactory?= null

        fun getInstance(context: Context): ViewModelFactory =
                instance?: synchronized(this){
                    instance?: ViewModelFactory(Injection.provideGopoxMovieUseCase(context))
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MovieTvViewModel::class.java)->{
                MovieTvViewModel(usecase) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java)->{
                DetailViewModel(usecase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java)->{
                FavoriteViewModel(usecase) as T
            }
            else -> throw Throwable("UnKnow ViewModel class: " + modelClass.name)
        }
    }


}