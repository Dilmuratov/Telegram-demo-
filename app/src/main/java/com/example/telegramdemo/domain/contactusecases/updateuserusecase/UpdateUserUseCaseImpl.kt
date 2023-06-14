package com.example.telegramdemo.domain.contactusecases.updateuserusecase

import com.example.telegramdemo.data.models.UserData
import com.example.telegramdemo.domain.ContactsRepository

class UpdateUserUseCaseImpl(private val contactsRepository: ContactsRepository) : UpdateUserUseCase {
    override suspend fun execute(userData: UserData) {
        contactsRepository.updateUser(userData)
    }
}