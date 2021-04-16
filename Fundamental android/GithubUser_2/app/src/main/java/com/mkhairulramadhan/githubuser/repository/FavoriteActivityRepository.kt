package com.mkhairulramadhan.githubuser.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mkhairulramadhan.githubuser.db.dao.UserDao
import com.mkhairulramadhan.githubuser.db.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteActivityRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<UserEntity>> = userDao.getAllUser()
//    val userDetail = MutableLiveData<UserEntity>()

    suspend fun addUser(user: UserEntity){
        userDao.addUser(user)
    }

    suspend fun deleteUser(username: String){
        userDao.deleteUser(username)
    }

//    suspend fun getUserDetail(username: String){
//        GlobalScope.launch(Dispatchers.IO) {
//            Log.d("dataku", "data detail: ${userDao.getUserDetail(username)}")
//            userDetail.value = userDao.getUserDetail(username)
//        }
//    }

}