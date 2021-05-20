package com.mkhairulramadhan.simplecleanarchitecture.data

import com.mkhairulramadhan.simplecleanarchitecture.domain.MessageEntity

class MessageDataSource: IMessageDataSource {
    override fun getMessageFromSource(name: String): MessageEntity =
        MessageEntity("Hello $name Welcome to clean architecture")
}