package com.mkhairulramadhan.githubuser.viewModel

import android.app.Application
import android.text.BoringLayout
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mkhairulramadhan.githubuser.db.entities.UserEntity
import com.mkhairulramadhan.githubuser.db.provider.UserDatabase
import com.mkhairulramadhan.githubuser.repository.FavoriteActivityRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteActivityViewModel(application: Application): AndroidViewModel(application) {

    val readAllUser: LiveData<List<UserEntity>>
    val userDetail = MutableLiveData<UserEntity>()
    private val userDao = UserDatabase.getDatabase(application).userDao()
    private val repository: FavoriteActivityRepository = FavoriteActivityRepository(userDao)

    init {
        readAllUser = repository.readAllData
    }

    fun addUser(user: UserEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser((user))
        }
    }

    fun deleteUser(username: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteUser(username)
        }
    }

    fun getUserDetail(username: String){
        viewModelScope.launch(Dispatchers.IO) {
            userDetail.postValue(userDao.getUserDetail(username))
            Log.d("dataku", "data detail: ${userDao.getUserDetail(username)}")
        }
    }

    fun getUserByUsername(): LiveData<UserEntity>{
        return userDetail
    }



}