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

    private val _database = database

    override suspend fun getAllMessages(groupPath: String): Flow<List<MessageData>> = flow {
        emit(
            _database.getReference(groupPath).get().await().children.mapNotNull {
                MessageData(
                    messageId = it.key.toString(),
                    message = it.child("message").value.toString(),
                    userId = it.child("userId").value.toString(),
                    time = it.child("time").value.toString(),
                    username = it.child("username").toString()
                )
            }
        )
    }.catch { it.printStackTrace() }

    override suspend fun addMessage(messageData: MessageData, groupPath: String) {
        _database.getReference(groupPath).child(messageData.messageId)
            .setValue(messageData)
            .addOnSuccessListener { Log.d("TTTT", "MessageData adding: Success") }
            .addOnFailureListener { Log.d("TTTT", "MessageData adding: Failure") }
    }
}