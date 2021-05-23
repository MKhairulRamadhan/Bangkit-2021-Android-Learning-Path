package com.mkhairulramadhan.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel (
        var id: Int,
        var title: String? = null,
        var backDropImage: String? = null,
        var posterImage: String? = null,
        var year: String? = null,
        var star: String? = null,
        var language: String? = null,
        var synopsis: String? = null,
        var favorite: Boolean = false
): Parcelable