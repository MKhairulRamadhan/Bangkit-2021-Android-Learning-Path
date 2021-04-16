package com.mkhairulramadhan.githubuser.repository

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mkhairulramadhan.githubuser.db.helper.MappingHelper
import com.mkhairulramadhan.githubuser.db.helper.UserProvider.Companion.CONTENT_URI
import com.mkhairulramadhan.githubuser.db.network.model.Items

class FavoriteRepository(val application: Application) {

    val favoriteListUser = MutableLiveData<ArrayList<Items>>()

    fun setFavoriteListUser(context: Context) {
        val cursor = context.contentResolver.query(CONTENT_URI,null, null,null,null)
        val favoriteListData = MappingHelper.mapCursorToArrayList(cursor)
        favoriteListUser.postValue(favoriteListData)
    }


}