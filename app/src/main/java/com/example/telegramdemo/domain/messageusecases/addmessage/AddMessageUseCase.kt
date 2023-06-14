package com.example.telegramdemo.domain.messageusecases.addmessage

import com.example.telegramdemo.data.models.MessageData

interface AddMessageUseCase {
    suspend fun execute(messageData: MessageData, groupPath: String)
}