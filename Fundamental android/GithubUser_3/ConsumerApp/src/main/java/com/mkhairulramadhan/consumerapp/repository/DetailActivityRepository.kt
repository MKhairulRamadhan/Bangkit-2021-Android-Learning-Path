package com.mkhairulramadhan.consumerapp.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.mkhairulramadhan.consumerapp.db.network.RetrofitConfig
import com.mkhairulramadhan.consumerapp.db.network.model.DetailUserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivityRepository(val application: Application) {
    val userData = MutableLiveData<DetailUserModel>()
    val progressBar = MutableLiveData<Boolean>()

    fun getUser(username: String){
        progressBar.value = true
        val service = RetrofitConfig.apiUserClient
        service.userDetail(username).enqueue(object : Callback<DetailUserModel>{
            override fun onResponse(call: Call<DetailUserModel>, response: Response<DetailUserModel>) {
                userData.value = response.body()
                progressBar.value = false
            }

            override fun onFailure(call: Call<DetailUserModel>, t: Throwable) {
                progressBar.value = false
                Toast.makeText(application, t.message.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }

}