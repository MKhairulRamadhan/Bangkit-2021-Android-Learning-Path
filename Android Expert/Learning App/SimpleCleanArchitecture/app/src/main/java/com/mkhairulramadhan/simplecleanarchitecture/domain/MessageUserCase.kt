package com.mkhairulramadhan.simplecleanarchitecture.domain

interface MessageUserCase {
    fun getMessage(name: String): MessageEntity
}