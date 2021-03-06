package com.mkhairulramadhan.githubuser.db.helper

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.mkhairulramadhan.githubuser.db.database.AppDatabase

class UserProvider : ContentProvider() {

    companion object {
        private const val AUTHORITY = "com.mkhairulramadhan.githubuser"
        private const val SCHEME = "content"
        private const val TABLE_NAME = "user_fav"
        private const val FAVORITE = 1
        private const val FAVORITE_ID = 2

        private lateinit var appDatabase: AppDatabase

        val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build()

        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            sUriMatcher.addURI(AUTHORITY, TABLE_NAME, FAVORITE)
            sUriMatcher.addURI(AUTHORITY, "$TABLE_NAME/#", FAVORITE_ID)
        }
    }

    override fun onCreate(): Boolean {
        appDatabase = AppDatabase.getDatabase(context as Context)
        appDatabase.userDao()
        return true
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return when (sUriMatcher.match(uri)) {
            FAVORITE -> appDatabase.userDao().getUsers()
            FAVORITE_ID -> appDatabase.userDao().getUserById(uri.lastPathSegment!!.toInt())
            else -> null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val addId: Long = when (FAVORITE) {
            sUriMatcher.match(uri) -> appDatabase.userDao().insertUser(values.let {
                MappingHelper.convertFromContentValuesIntoItems(
                    it!!
                )
            })
            else -> 0
        }
        context?.contentResolver?.notifyChange(CONTENT_URI, null)
        return Uri.parse("$CONTENT_URI/$addId")
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val deleteId: Int = when (FAVORITE_ID) {
            sUriMatcher.match(uri) -> appDatabase.userDao().deleteUser(uri.lastPathSegment!!.toInt())
            else -> 0
        }
        context?.contentResolver?.notifyChange(CONTENT_URI, null)

        return deleteId
    }

}