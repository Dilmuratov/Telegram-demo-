package com.example.telegramdemo.di

import com.example.telegramdemo.data.repository.GroupsRepositoryImpl
import com.example.telegramdemo.data.repository.MessageRepositoryImpl
import com.example.telegramdemo.domain.GroupsRepository
import com.example.telegramdemo.domain.MessagesRepository
import com.example.telegramdemo.domain.groupusecases.addgroup.AddGroupUseCase
import com.example.telegramdemo.domain.groupusecases.addgroup.AddGroupUseCaseImpl
import com.example.telegramdemo.domain.groupusecases.getallgroups.GetAllGroupsUseCase
import com.example.telegramdemo.domain.groupusecases.getallgroups.GetAllGroupsUseCaseImpl
import com.example.telegramdemo.domain.groupusecases.updategroup.UpdateGroupUseCase
import com.example.telegramdemo.domain.groupusecases.updategroup.UpdateGroupUseCaseImpl
import com.example.telegramdemo.domain.messageusecases.addmessage.AddMessageUseCase
import com.example.telegramdemo.domain.messageusecases.addmessage.AddMessageUseCaseImpl
import com.example.telegramdemo.domain.messageusecases.getallmessages.GetAllMessagesUseCase
import com.example.telegramdemo.domain.messageusecases.getallmessages.GetAllMessagesUseCaseImpl
import org.koin.dsl.module

val dataModule = module {
    single<GroupsRepository> {
        GroupsRepositoryImpl(get())
    }

    factory<GetAllGroupsUseCase> {
        GetAllGroupsUseCaseImpl(get())
    }

    factory<AddGroupUseCase> {
        AddGroupUseCaseImpl(get())
    }

    factory<UpdateGroupUseCase> {
        UpdateGroupUseCaseImpl(get())
    }

    single<MessagesRepository> {
        MessageRepositoryImpl(get())
    }

    factory<GetAllMessagesUseCase> {
        GetAllMessagesUseCaseImpl(get())
    }

    factory<AddMessageUseCase> {
        AddMessageUseCaseImpl(get())
    }
}