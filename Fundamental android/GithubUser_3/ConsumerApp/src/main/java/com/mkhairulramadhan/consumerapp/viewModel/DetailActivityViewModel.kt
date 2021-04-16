package com.mkhairulramadhan.consumerapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mkhairulramadhan.consumerapp.db.network.model.DetailUserModel
import com.mkhairulramadhan.consumerapp.repository.DetailActivityRepository

class DetailActivityViewModel(application: Application): AndroidViewModel(application)
{

    private val repository = DetailActivityRepository(application)
    val userData : LiveData<DetailUserModel>
    val progressBar : LiveData<Boolean>

    init {
        this.userData = repository.userData
        this.progressBar = repository.progressBar
    }

    fun getUser(username: String){
        repository.getUser(username)
    }

}