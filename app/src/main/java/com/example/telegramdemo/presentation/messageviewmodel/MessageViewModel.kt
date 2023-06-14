package com.example.telegramdemo.presentation.messageviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.telegramdemo.data.models.MessageData

abstract class MessageViewModel : ViewModel() {

    abstract val getAllMessagesLiveData: LiveData<List<MessageData>>

    abstract suspend fun getAllMessages(groupPath: String)

    abstract suspend fun addMessage(messageData: MessageData, groupPath: String)

}