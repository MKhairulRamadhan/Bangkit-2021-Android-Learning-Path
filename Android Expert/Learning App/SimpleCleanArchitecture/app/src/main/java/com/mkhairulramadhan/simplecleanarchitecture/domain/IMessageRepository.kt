package com.mkhairulramadhan.simplecleanarchitecture.domain

interface IMessageRepository {
    fun getWecomeMessage(name: String): MessageEntity
}