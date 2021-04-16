package com.mkhairulramadhan.githubuser.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Items(
        val id: Int,
        val avatar_url: String?,
        val login: String,
        val type: String?
) : Parcelable