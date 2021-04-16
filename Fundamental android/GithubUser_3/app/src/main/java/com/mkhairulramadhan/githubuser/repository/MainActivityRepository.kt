package com.mkhairulramadhan.githubuser.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.mkhairulramadhan.githubuser.db.network.RetrofitConfig
import com.mkhairulramadhan.githubuser.db.network.model.Items
import com.mkhairulramadhan.githubuser.db.network.model.UserDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityRepository(val application: Application){

    val userList = MutableLiveData<List<Items>>()
    val progressBar = MutableLiveData<Boolean>()

    fun searchUser(searchUser: String){
        progressBar.value = true
        val service = RetrofitConfig.apiUserClient
        service.userSearch(searchUser).enqueue(object : Callback<UserDataModel>{
            override fun onResponse(call: Call<UserDataModel>, response: Response<UserDataModel>) {
                userList.value = response.body()!!.items
                progressBar.value = false
            }

            override fun onFailure(call: Call<UserDataModel>, t: Throwable) {
                progressBar.value = false
                Toast.makeText(application, t.message.toString(), Toast.LENGTH_SHORT).show()
                Log.d("dataku", "error : ${t.message.toString()}")
            }

        })
    }

}