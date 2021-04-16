package com.mkhairulramadhan.consumerapp.db.databaseHelper

import android.database.Cursor
import com.mkhairulramadhan.consumerapp.db.network.model.Items

class MappingHelper {

    companion object{

        fun mapCursorToArrayList(favoritesCursor: Cursor?): ArrayList<Items> {
            val favoriteList = ArrayList<Items>()

            favoritesCursor?.apply {
                while (moveToNext()) {
                    val id = getInt(getColumnIndexOrThrow("id"))
                    val avatarUrl = getString(getColumnIndexOrThrow("avatar_url"))
                    val login = getString(getColumnIndexOrThrow("login"))
                    val type = getString(getColumnIndexOrThrow("type"))

                    favoriteList.add(
                        Items(
                            id,
                            avatarUrl,
                            login,
                            type
                        )
                    )

                }
            }

            return favoriteList
        }
    }

}