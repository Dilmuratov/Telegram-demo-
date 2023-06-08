package com.example.telegramdemo.presentation.groupviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.telegramdemo.data.models.ChatData
import com.example.telegramdemo.domain.groupusecases.addgroup.AddGroupUseCase
import com.example.telegramdemo.domain.groupusecases.getallgroups.GetAllGroupsUseCase
import com.example.telegramdemo.domain.groupusecases.updategroup.UpdateGroupUseCase

class GroupsViewModelImpl(
    private val getAllGroupsUseCase: GetAllGroupsUseCase,
    private val addGroupUseCase: AddGroupUseCase,
    private val updateGroupUseCase: UpdateGroupUseCase
) : GroupsViewModel() {

    private val _getAllGroupsLiveData = MutableLiveData<List<ChatData>>()
    override val getAllGroupsLiveData: LiveData<List<ChatData>> get() = _getAllGroupsLiveData

    override suspend fun getAllGroups() {
        getAllGroupsUseCase.execute().collect() {
            _getAllGroupsLiveData.value = it
        }
    }

    override suspend fun addGroup(chatData: ChatData) {
        addGroupUseCase.execute(chatData)
    }

    override suspend fun updateGroup(chatData: ChatData) {
        updateGroupUseCase.execute(chatData)
    }
}