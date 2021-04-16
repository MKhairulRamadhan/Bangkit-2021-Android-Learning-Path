package com.mkhairulramadhan.githubuser.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.mkhairulramadhan.githubuser.db.network.RetrofitConfig
import com.mkhairulramadhan.githubuser.db.network.model.Items
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersFragmentRepository(val application: Application) {

    val listFollowers = MutableLiveData<List<Items>>()
    val progressBar = MutableLiveData<Boolean>()

    fun getFollowers(username: String){
        progressBar.value = true
        val service = RetrofitConfig.apiUserClient
        service.userFollower(username).enqueue(object : Callback<List<Items>> {
            override fun onResponse(call: Call<List<Items>>, response: Response<List<Items>>) {
                listFollowers.value = response.body()
                progressBar.value = false
            }

            override fun onFailure(call: Call<List<Items>>, t: Throwable) {
                progressBar.value = false
                Toast.makeText(application, t.message.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }

}