package com.mkhairulramadhan.simplecleanarchitecture.data

import com.mkhairulramadhan.simplecleanarchitecture.domain.IMessageRepository
import com.mkhairulramadhan.simplecleanarchitecture.domain.MessageEntity

class MessageRepository(private val messageDataSource: IMessageDataSource): IMessageRepository {
    override fun getWecomeMessage(name: String): MessageEntity = messageDataSource.getMessageFromSource(name)
}