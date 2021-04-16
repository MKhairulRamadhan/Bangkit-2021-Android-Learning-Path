package com.mkhairulramadhan.githubuser.db.network.model

data class UserDataModel(
        val incomplete_results: Boolean,
        val items: List<Items>,
        val total_count: Int
)