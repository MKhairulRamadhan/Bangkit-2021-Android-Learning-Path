package com.mkhairulramadhan.core.data.remote

data class TvDataItem(
        val id: Int,
        val name: String? = null,
        val backdrop_path: String? = null,
        val poster_path: String? = null,
        val first_air_date: String? = null,
        val vote_average: Double? = null,
        val original_language: String? = null,
        val overview: String? = null
)