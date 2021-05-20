package com.mkhairulramadhan.submission1moviecatalog.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieTvModel (
    val id: Int,
    val title: String? = null,
    val backDropImage: String? = null,
    val posterImage: String? = null,
    val year: String? = null,
    val star: String? = null,
    val language: String? = null,
    val duration: String? = null,
    val tag: String? = null,
    val synopsis: String? = null
): Parcelable