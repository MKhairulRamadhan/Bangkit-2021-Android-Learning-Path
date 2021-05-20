package com.mkhairulramadhan.simplecleanarchitecture.data

import com.mkhairulramadhan.simplecleanarchitecture.domain.MessageEntity

interface IMessageDataSource {
    fun getMessageFromSource(name: String): MessageEntity
}