package com.example.telegramdemo.domain.groupusecases.addgroup

import com.example.telegramdemo.data.models.ChatData

interface AddGroupUseCase {
    suspend fun execute(chatData: ChatData)
}