package com.mkhairulramadhan.simplecleanarchitecture.di

import com.mkhairulramadhan.simplecleanarchitecture.data.IMessageDataSource
import com.mkhairulramadhan.simplecleanarchitecture.data.MessageDataSource
import com.mkhairulramadhan.simplecleanarchitecture.data.MessageRepository
import com.mkhairulramadhan.simplecleanarchitecture.domain.IMessageRepository
import com.mkhairulramadhan.simplecleanarchitecture.domain.MessageIteractor
import com.mkhairulramadhan.simplecleanarchitecture.domain.MessageUserCase

object Injection {

    fun provideUseCase(): MessageUserCase{
        val messageRepository = provideRepository()
        return MessageIteractor(messageRepository)
    }

    private fun provideRepository(): IMessageRepository {
        val messageDataSource = provideDataSource()
        return MessageRepository(messageDataSource)
    }

    private fun provideDataSource(): IMessageDataSource {
        return MessageDataSource()
    }

}