package com.mkhairulramadhan.consumerapp.db.network.model

data class UserDataModel(
        val incomplete_results: Boolean,
        val items: List<Items>,
        val total_count: Int
)