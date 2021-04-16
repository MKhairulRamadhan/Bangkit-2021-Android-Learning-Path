package com.mkhairulramadhan.githubuser.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val avatar_url: String?,
    val login: String,
    val type: String?
)