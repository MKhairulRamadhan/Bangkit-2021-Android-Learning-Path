package com.mkhairulramadhan.githubuser.viewModel

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mkhairulramadhan.githubuser.db.network.model.DetailUserModel
import com.mkhairulramadhan.githubuser.repository.DetailActivityRepository

class DetailActivityViewModel(application: Application): AndroidViewModel(application)
{

    private val repository = DetailActivityRepository(application)
    val userData : LiveData<DetailUserModel>
    val favoriteUser: LiveData<Cursor>
    val progressBar : LiveData<Boolean>

    init {
        this.userData = repository.userData
        this.progressBar = repository.progressBar
        favoriteUser = repository.favoriteUser
    }

    fun getUser(username: String){
        repository.getUser(username)
    }

    fun setFavoriteUser(userfav: ContentValues, context: Context){
        repository.setFavoriteUser(userfav, context)
    }

    fun getFavoriteUserById(id: Int, context: Context){
        repository.getFavoriteUserById(id, context)
    }

    fun deleteFavoriteUser(id: Int, context: Context) {
        repository.deleteFavoriteUser(id, context)
    }

}