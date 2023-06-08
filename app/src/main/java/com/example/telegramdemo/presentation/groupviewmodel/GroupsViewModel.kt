package com.example.telegramdemo.presentation.groupviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.telegramdemo.data.models.ChatData

abstract class GroupsViewModel : ViewModel() {

    abstract val getAllGroupsLiveData: LiveData<List<ChatData>>

    abstract suspend fun getAllGroups()

    abstract suspend fun addGroup(chatData: ChatData)

    abstract suspend fun updateGroup(chatData: ChatData)

}