package com.example.telegramdemo.domain.groupusecases.getallgroups

import com.example.telegramdemo.data.models.ChatData
import com.example.telegramdemo.domain.GroupsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllGroupsUseCaseImpl(private val groupsRepository: GroupsRepository) :
    GetAllGroupsUseCase {
    override suspend fun execute(): Flow<List<ChatData>> = flow {
        groupsRepository.getAllGroups().collect() {
            emit(it)
        }
    }
}