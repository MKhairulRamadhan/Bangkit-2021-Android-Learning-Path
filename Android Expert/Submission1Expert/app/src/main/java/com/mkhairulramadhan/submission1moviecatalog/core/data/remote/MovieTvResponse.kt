package com.mkhairulramadhan.submission1moviecatalog.core.data.remote

import com.google.gson.annotations.SerializedName

data class MovieTvResponse<T>(
    @field:SerializedName("results")
    val results: List<T>
)