package com.example.telegramdemo.domain.messageusecases.getallmessages

import com.example.telegramdemo.data.models.MessageData
import kotlinx.coroutines.flow.Flow

interface GetAllMessagesUseCase {
    suspend fun execute(groupPath: String): Flow<List<MessageData>>
}