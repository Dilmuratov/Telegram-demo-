package com.example.telegramdemo.domain.contactusecases.getallcontacts

import com.example.telegramdemo.data.models.UserData
import com.example.telegramdemo.domain.ContactsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllContactsUseCaseImpl(private val contactsRepository: ContactsRepository) :
    GetAllContactsUseCase {
    override suspend fun execute(): Flow<List<UserData>> = flow {
        contactsRepository.getAllContacts().collect {
            emit(it)
        }
    }
}