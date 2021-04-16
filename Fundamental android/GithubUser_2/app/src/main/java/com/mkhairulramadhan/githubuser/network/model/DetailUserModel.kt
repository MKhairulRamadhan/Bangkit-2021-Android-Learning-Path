package com.mkhairulramadhan.githubuser.network.model

data class DetailUserModel(
    val avatar_url: String,
    val id: Int,
    val company: String,
    val followers: Int,
    val following: Int,
    val location: String,
    val login: String,
    val name: String,
    val public_repos: Int,
)