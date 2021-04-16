package com.mkhairulramadhan.consumerapp.repository

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mkhairulramadhan.consumerapp.db.databaseHelper.MappingHelper
import com.mkhairulramadhan.consumerapp.db.databaseHelper.UriContent.Companion.CONTENT_URI
import com.mkhairulramadhan.consumerapp.db.network.model.Items

class FavoriteRepository(val application: Application) {

    val favoriteListUser = MutableLiveData<ArrayList<Items>>()

    fun setFavoriteListUser(context: Context) {
        val cursor = context.contentResolver.query(CONTENT_URI,null, null,null,null)
        val favoriteListData = MappingHelper.mapCursorToArrayList(cursor)
        favoriteListUser.postValue(favoriteListData)
        Log.d("setFavoriteListUser", "tampil : $CONTENT_URI")
    }

}