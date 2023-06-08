package com.example.telegramdemo.domain.groupusecases.updategroup

import com.example.telegramdemo.data.models.ChatData

interface UpdateGroupUseCase {
    suspend fun execute(chatData: ChatData)
}