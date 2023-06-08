package com.example.telegramdemo.domain.groupusecases.addgroup

import com.example.telegramdemo.data.models.ChatData
import com.example.telegramdemo.domain.GroupsRepository

class AddGroupUseCaseImpl(private val groupsRepository: GroupsRepository) : AddGroupUseCase {
    override suspend fun execute(chatData: ChatData) {
        groupsRepository.addGroup(chatData)
    }
}