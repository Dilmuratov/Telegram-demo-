package com.example.telegramdemo.data.repository

import android.util.Log
import com.example.telegramdemo.data.models.MessageData
import com.example.telegramdemo.domain.MessagesRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class MessageRepositoryImpl(database: FirebaseDatabase) : MessagesRepository {

    private val getAllMessagesCollectionRef = database.getReference("1322018752992")

    override suspend fun getAllMessages(): Flow<List<MessageData>> = flow {
        emit(
            getAllMessagesCollectionRef.get().await().children.mapNotNull {
                MessageData(
                    messageId = it.key.toString(),
                    message = it.child("message").value.toString(),
                    userId = it.child("userId").value.toString(),
                    time = it.child("time").value.toString()
                )
            }
        )
    }.catch { it.printStackTrace() }

    override suspend fun addMessage(messageData: MessageData) {
        getAllMessagesCollectionRef.child(messageData.messageId)
            .setValue(messageData)
            .addOnSuccessListener { Log.d("TTTT", "MessageData adding: Success") }
            .addOnFailureListener { Log.d("TTTT", "MessageData adding: Failure") }
    }
}