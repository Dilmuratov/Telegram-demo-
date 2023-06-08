package com.example.telegramdemo.domain.messageusecases.getallmessages

import com.example.telegramdemo.data.models.MessageData
import com.example.telegramdemo.domain.MessagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllMessagesUseCaseImpl(private val messagesRepository: MessagesRepository) :
    GetAllMessagesUseCase {
    override suspend fun execute(): Flow<List<MessageData>> = flow {
        messagesRepository.getAllMessages().collect() {
            emit(it)
        }
    }
}