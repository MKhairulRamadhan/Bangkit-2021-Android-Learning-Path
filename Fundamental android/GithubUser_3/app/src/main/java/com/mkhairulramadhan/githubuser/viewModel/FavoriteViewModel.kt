package com.mkhairulramadhan.githubuser.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mkhairulramadhan.githubuser.db.network.model.Items
import com.mkhairulramadhan.githubuser.repository.FavoriteRepository

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private val repository = FavoriteRepository(application)
    val favoriteListUser: LiveData<ArrayList<Items>>

    init {
        favoriteListUser = repository.favoriteListUser
    }

    fun setFavoriteListUser(context: Context){
        repository.setFavoriteListUser(context)
    }

}