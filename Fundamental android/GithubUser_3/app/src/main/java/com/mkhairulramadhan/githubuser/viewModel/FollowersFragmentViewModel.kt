package com.mkhairulramadhan.githubuser.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mkhairulramadhan.githubuser.db.network.model.Items
import com.mkhairulramadhan.githubuser.repository.FollowersFragmentRepository

class FollowersFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val repository = FollowersFragmentRepository(application)
    val listFollowers: LiveData<List<Items>>
    val progressBar: LiveData<Boolean>

    init {
        this.listFollowers = repository.listFollowers
        this.progressBar = repository.progressBar
    }

    fun getFollowers(username: String){
        repository.getFollowers(username)
    }


}