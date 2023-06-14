package com.example.telegramdemo.domain.contactusecases.updateuserusecase

import com.example.telegramdemo.data.models.UserData

interface UpdateUserUseCase {
    abstract suspend fun execute(userData: UserData)
}