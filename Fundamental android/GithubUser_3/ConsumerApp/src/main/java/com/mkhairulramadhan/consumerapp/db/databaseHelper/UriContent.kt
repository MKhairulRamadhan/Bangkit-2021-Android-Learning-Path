package com.mkhairulramadhan.consumerapp.db.databaseHelper

import android.net.Uri

class UriContent {
    companion object {

        private const val AUTHORITY = "com.mkhairulramadhan.githubuser"
        private const val SCHEME = "content"
        private const val TABLE_NAME = "user_fav"

        val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build()
    }
}