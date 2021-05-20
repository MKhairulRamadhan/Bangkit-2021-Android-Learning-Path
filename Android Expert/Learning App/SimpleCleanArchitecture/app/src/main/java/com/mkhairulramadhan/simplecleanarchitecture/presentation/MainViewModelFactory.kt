package com.mkhairulramadhan.simplecleanarchitecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mkhairulramadhan.simplecleanarchitecture.di.Injection
import com.mkhairulramadhan.simplecleanarchitecture.domain.MessageUserCase

class MainViewModelFactory(private var messageUserCase: MessageUserCase):
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: MainViewModelFactory? = null

        fun getInstance(): MainViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: MainViewModelFactory(Injection.provideUseCase())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(messageUserCase) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

}