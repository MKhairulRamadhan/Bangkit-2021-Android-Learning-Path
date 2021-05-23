package com.mkhairulramadhan.core.data.remote

data class MovieDataItem(
        val id: Int,
        val title: String? = null,
        val backdrop_path: String? = null,
        val poster_path: String? = null,
        val release_date: String? = null,
        val vote_average: Double? = null,
        val original_language: String? = null,
        val overview: String? = null
)