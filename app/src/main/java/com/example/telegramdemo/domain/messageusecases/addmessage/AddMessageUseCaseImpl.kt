package com.example.telegramdemo.domain.messageusecases.addmessage

import com.example.telegramdemo.data.models.MessageData
import com.example.telegramdemo.domain.MessagesRepository

class AddMessageUseCaseImpl(private val messagesRepository: MessagesRepository) :
    AddMessageUseCase {
    override suspend fun execute(messageData: MessageData, groupPath: String) {
        messagesRepository.addMessage(messageData, groupPath)
    }
}