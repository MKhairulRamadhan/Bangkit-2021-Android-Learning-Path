package com.mkhairulramadhan.submission1moviecatalog.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mkhairulramadhan.submission1moviecatalog.data.GopoxMovieRepository
import com.mkhairulramadhan.submission1moviecatalog.di.Injection

class ViewModelFactory private constructor(private val repository: GopoxMovieRepository) : ViewModelProvider.NewInstanceFactory() {
    companion object{
        @Volatile
        private var instance: ViewModelFactory?= null

        fun getInstance(context: Context): ViewModelFactory =
                instance?: synchronized(this){
                    instance?: ViewModelFactory(Injection.injectRepository(context))
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MovieTvViewModel::class.java)->{
                MovieTvViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java)->{
                DetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java)->{
                FavoriteViewModel(repository) as T
            }
            else -> throw Throwable("UnKnow ViewModel class: " + modelClass.name)
        }
    }


}