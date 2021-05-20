package com.mkhairulramadhan.simplecleanarchitecture.domain

class MessageIteractor(private val messageRepository: IMessageRepository): MessageUserCase {
    override fun getMessage(name: String): MessageEntity {
        return messageRepository.getWecomeMessage(name)
    }
}