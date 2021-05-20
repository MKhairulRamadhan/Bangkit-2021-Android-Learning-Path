package com.mkhairulramadhan.simplecleanarchitecture.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mkhairulramadhan.simplecleanarchitecture.domain.MessageEntity
import com.mkhairulramadhan.simplecleanarchitecture.domain.MessageUserCase

class MainViewModel(private val messageUserCase: MessageUserCase): ViewModel() {
    private val _message = MutableLiveData<MessageEntity>()
    val message: LiveData<MessageEntity>
        get() = _message

    fun setName(name: String){
        _message.value = messageUserCase.getMessage(name)
    }
}