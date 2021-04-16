package com.mkhairulramadhan.githubuser.db.network.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user_fav")
@Parcelize
data class Items(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: Int? = null,

        @ColumnInfo(name = "avatar_url")
        val avatar_url: String? = null,

        @ColumnInfo(name = "login")
        val login: String? = null,

        @ColumnInfo(name = "type")
        val type: String? = null
) : Parcelable