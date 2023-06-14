package com.example.telegramdemo.domain.contactusecases.getallcontacts

import com.example.telegramdemo.data.models.UserData
import kotlinx.coroutines.flow.Flow

interface GetAllContactsUseCase {
    suspend fun execute(): Flow<List<UserData>>
}