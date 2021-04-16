package com.mkhairulramadhan.githubuser.viewModel

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mkhairulramadhan.githubuser.db.helper.UserProvider.Companion.CONTENT_URI
import com.mkhairulramadhan.githubuser.db.network.model.Items
import com.mkhairulramadhan.githubuser.repository.DetailActivityRepository
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