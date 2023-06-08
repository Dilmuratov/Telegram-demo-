package com.example.telegramdemo.domain.groupusecases.updategroup

import com.example.telegramdemo.data.models.ChatData
import com.example.telegramdemo.domain.GroupsRepository

class UpdateGroupUseCaseImpl(private val groupsRepository: GroupsRepository) : UpdateGroupUseCase {

    override suspend fun execute(chatData: ChatData) {
        groupsRepository.updateGroup(chatData)
    }
}