package com.mkhairulramadhan.submission1moviecatalog.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieTvModel (
    val id: String,
    val title: String,
    val image: Int,
    val duration: String,
    val age: String,
    val year: String,
    val star: String,
    val director: String,
    val tag: String,
    val synopsis: String
): Parcelable