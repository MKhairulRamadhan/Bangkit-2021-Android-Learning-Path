package com.mkhairulramadhan.core.data.remote

import com.google.gson.annotations.SerializedName

data class MovieTvResponse<T>(
    @field:SerializedName("results")
    val results: List<T>
)