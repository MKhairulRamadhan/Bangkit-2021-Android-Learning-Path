package com.mkhairulramadhan.githubuser.network

import com.mkhairulramadhan.githubuser.network.model.DetailUserModel
import com.mkhairulramadhan.githubuser.network.model.Items
import com.mkhairulramadhan.githubuser.network.model.UserDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserNetwork {

    @GET("search/users")
    fun userSearch(@Query("q")searchUser: String): Call<UserDataModel>

    @GET("users/{username}")
    fun userDetail(@Path("username") searchUser: String): Call<DetailUserModel>

    @GET("users/{username}/followers")
    fun userFollower(@Path("username")searchUser: String): Call<List<Items>>

    @GET("users/{username}/following")
    fun userFollowing(@Path("username")searchUser: String): Call<List<Items>>

}
