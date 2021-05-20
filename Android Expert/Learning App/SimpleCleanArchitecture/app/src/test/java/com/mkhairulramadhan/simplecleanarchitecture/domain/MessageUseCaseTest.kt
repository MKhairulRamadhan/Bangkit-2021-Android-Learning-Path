package com.mkhairulramadhan.simplecleanarchitecture.domain

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MessageUseCaseTest {
    private lateinit var messageUseCase: MessageUserCase

    @Mock
    private lateinit var messageRepository: IMessageRepository

    @Before
    fun setUp() {
        messageUseCase = MessageIteractor(messageRepository)
        val dummyMessage = MessageEntity("Hello $NAME Welcome to Clean Architecture")
        `when`(messageRepository.getWecomeMessage(NAME)).thenReturn(dummyMessage)
    }

    @Test
    fun `should get data from repository`() {
        val message = messageUseCase.getMessage(NAME)

        verify(messageRepository).getWecomeMessage(NAME)
        verifyNoMoreInteractions(messageRepository)
        Assert.assertEquals("Hello $NAME Welcome to Clean Architecture", message.welcomeMessage)
    }

    companion object {
        const val NAME = "Dicoding"
    }
}