package com.example.telegramdemo.domain

import com.example.telegramdemo.data.models.ChatData
import kotlinx.coroutines.flow.Flow

interface GroupsRepository {

    suspend fun getAllGroups(): Flow<List<ChatData>>

    suspend fun addGroup(chatData: ChatData)

    suspend fun updateGroup(chatData: ChatData)
}