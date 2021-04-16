package com.mkhairulramadhan.githubuser.repository

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.mkhairulramadhan.githubuser.db.helper.UserProvider
import com.mkhairulramadhan.githubuser.db.network.RetrofitConfig
import com.mkhairulramadhan.githubuser.db.network.model.DetailUserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivityRepository(val application: Application) {
    private lateinit var uriWithId: Uri
    val favoriteUser = MutableLiveData<Cursor>()
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

    fun setFavoriteUser(userfav: ContentValues, context: Context){
        context.contentResolver.insert(UserProvider.CONTENT_URI, userfav)
        Log.d("setFavoriteUser", "tampil: ${UserProvider.CONTENT_URI}")
    }

    fun getFavoriteUserById(id: Int, context: Context){
        uriWithId = Uri.parse("${UserProvider.CONTENT_URI}/$id")
        val cursor = context.contentResolver.query(uriWithId, null, null, null, null)
        favoriteUser.postValue(cursor)
        Log.d("setFavoriteById", "tampil : $uriWithId")
    }

    fun deleteFavoriteUser(id: Int, context: Context) {
        uriWithId = Uri.parse("${UserProvider.CONTENT_URI}/$id")
        context.contentResolver.delete(uriWithId, null, null)
        Log.d("deleteFavoriteUser", "display : $uriWithId")
    }

}