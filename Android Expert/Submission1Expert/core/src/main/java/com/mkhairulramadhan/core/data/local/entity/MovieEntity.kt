package com.mkhairulramadhan.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieEntity")
data class MovieEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "backDropImage")
    var backDropImage: String? = null,

    @ColumnInfo(name = "posterImage")
    var posterImage: String? = null,

    @ColumnInfo(name = "year")
    var year: String? = null,

    @ColumnInfo(name = "star")
    var star: String? = null,

    @ColumnInfo(name = "language")
    var language: String? = null,

    @ColumnInfo(name = "synopsis")
    var synopsis: String? = null,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)