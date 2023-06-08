package com.example.telegramdemo.domain

import com.example.telegramdemo.data.models.MessageData
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {

    suspend fun getAllMessages(): Flow<List<MessageData>>

    suspend fun addMessage(messageData: MessageData)
}