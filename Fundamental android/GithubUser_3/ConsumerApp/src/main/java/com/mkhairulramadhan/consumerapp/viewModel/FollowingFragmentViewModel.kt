package com.mkhairulramadhan.consumerapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mkhairulramadhan.consumerapp.db.network.model.Items
import com.mkhairulramadhan.consumerapp.repository.FollowingFragmentRepository

class FollowingFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val repository = FollowingFragmentRepository(application)
    val listFollowing: LiveData<List<Items>>
    val progressBar: LiveData<Boolean>

    init {
        this.listFollowing = repository.listFollowing
        this.progressBar = repository.progressBar
    }

    fun getFollowing(username: String){
        repository.getFollowing(username)
    }


}