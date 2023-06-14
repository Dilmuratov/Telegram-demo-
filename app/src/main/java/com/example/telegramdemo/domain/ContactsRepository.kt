package com.example.telegramdemo.domain

import com.example.telegramdemo.data.models.UserData
import kotlinx.coroutines.flow.Flow

interface ContactsRepository {

    suspend fun getAllContacts(): Flow<List<UserData>>

    suspend fun updateUser(userData: UserData)

}