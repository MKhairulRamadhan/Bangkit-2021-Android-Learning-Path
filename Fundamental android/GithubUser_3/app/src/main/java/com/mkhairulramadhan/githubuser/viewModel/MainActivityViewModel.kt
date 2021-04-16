package com.mkhairulramadhan.githubuser.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mkhairulramadhan.githubuser.db.network.model.Items
import com.mkhairulramadhan.githubuser.repository.MainActivityRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainActivityRepository(application)
    val userList: LiveData<List<Items>>
    val progressBar: LiveData<Boolean>

    init{
        this.userList = repository.userList
        this.progressBar = repository.progressBar
    }

    fun getUser(searchUser: String){
        repository.searchUser(searchUser)
    }


}