package com.example.telegramdemo.domain.groupusecases.getallgroups

import com.example.telegramdemo.data.models.ChatData
import kotlinx.coroutines.flow.Flow

interface GetAllGroupsUseCase {
    suspend fun execute(): Flow<List<ChatData>>
}