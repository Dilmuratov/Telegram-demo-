package com.example.telegramdemo.presentation.messageviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.telegramdemo.data.models.MessageData
import com.example.telegramdemo.domain.messageusecases.addmessage.AddMessageUseCase
import com.example.telegramdemo.domain.messageusecases.getallmessages.GetAllMessagesUseCase

class MessageViewModelImpl(
    private val getAllMessagesUseCase: GetAllMessagesUseCase,
    private val addMessageUseCase: AddMessageUseCase
) : MessageViewModel() {

    private val _getAllMessagesLiveData = MutableLiveData<List<MessageData>>()
    override val getAllMessagesLiveData: LiveData<List<MessageData>> get() = _getAllMessagesLiveData

    override suspend fun getAllMessages(groupPath: String) {
        getAllMessagesUseCase.execute(groupPath).collect() {
            _getAllMessagesLiveData.value = it
        }
    }

    override suspend fun addMessage(messageData: MessageData, groupPath: String) {
        addMessageUseCase.execute(messageData, groupPath)
    }
}