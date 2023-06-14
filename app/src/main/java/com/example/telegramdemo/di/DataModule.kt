package com.example.telegramdemo.di

import com.example.telegramdemo.data.repository.ContactsRepositoryImpl
import com.example.telegramdemo.data.repository.GroupsRepositoryImpl
import com.example.telegramdemo.data.repository.MessageRepositoryImpl
import com.example.telegramdemo.domain.ContactsRepository
import com.example.telegramdemo.domain.GroupsRepository
import com.example.telegramdemo.domain.MessagesRepository
import com.example.telegramdemo.domain.contactusecases.getallcontacts.GetAllContactsUseCase
import com.example.telegramdemo.domain.contactusecases.getallcontacts.GetAllContactsUseCaseImpl
import com.example.telegramdemo.domain.contactusecases.updateuserusecase.UpdateUserUseCase
import com.example.telegramdemo.domain.contactusecases.updateuserusecase.UpdateUserUseCaseImpl
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

    single<ContactsRepository> {
        ContactsRepositoryImpl(get())
    }

    factory<GetAllContactsUseCase> {
        GetAllContactsUseCaseImpl(get())
    }

    factory<UpdateUserUseCase> {
        UpdateUserUseCaseImpl(get())
    }
}