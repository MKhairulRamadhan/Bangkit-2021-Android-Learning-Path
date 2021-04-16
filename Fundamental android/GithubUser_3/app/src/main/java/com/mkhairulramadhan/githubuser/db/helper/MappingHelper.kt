package com.mkhairulramadhan.githubuser.db.helper

import android.content.ContentValues
import android.database.Cursor
import com.mkhairulramadhan.githubuser.db.network.model.Items

class MappingHelper {

    companion object {

        fun convertFromContentValuesIntoItems(contentValues: ContentValues): Items {
            val itemEntity: Items

            val id = contentValues.getAsInteger("id")
            val avatarUrl = contentValues.getAsString("avatar_url")
            val login = contentValues.getAsString("login")
            val type = contentValues.getAsString("type")

            itemEntity = Items(
                id,
                avatarUrl,
                login,
                type
                )
            return itemEntity
        }

        fun convertFromItemsToContentValues(itemEntity: Items): ContentValues {
            val values = ContentValues()

            values.put("id", itemEntity.id)
            values.put("avatar_url", itemEntity.avatar_url)
            values.put("login", itemEntity.login)
            values.put("type", itemEntity.type)

            return values
        }

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