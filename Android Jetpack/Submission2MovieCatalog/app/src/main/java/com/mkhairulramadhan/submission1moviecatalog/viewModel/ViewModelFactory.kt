package com.mkhairulramadhan.submission1moviecatalog.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mkhairulramadhan.submission1moviecatalog.data.GopoxMovieRepository
import com.mkhairulramadhan.submission1moviecatalog.di.Injection

class ViewModelFactory private constructor(private val repository: GopoxMovieRepository) : ViewModelProvider.NewInstanceFactory() {
    companion object{
        @Volatile
        private var instance: ViewModelFactory?= null

        fun getInstance(): ViewModelFactory =
                instance?: synchronized(this){
                    instance?: ViewModelFactory(Injection.injectRepository())
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
            else -> throw Throwable("UnKnow ViewModel class: " + modelClass.name)
        }
    }
}