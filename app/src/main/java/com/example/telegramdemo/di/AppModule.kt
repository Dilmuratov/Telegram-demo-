package com.example.telegramdemo.di

import com.example.telegramdemo.presentation.contactviewmodel.ContactsViewModel
import com.example.telegramdemo.presentation.contactviewmodel.ContactsViewModelImpl
import com.example.telegramdemo.presentation.groupviewmodel.GroupsViewModel
import com.example.telegramdemo.presentation.groupviewmodel.GroupsViewModelImpl
import com.example.telegramdemo.presentation.messageviewmodel.MessageViewModel
import com.example.telegramdemo.presentation.messageviewmodel.MessageViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<GroupsViewModel> {
        GroupsViewModelImpl(
            getAllGroupsUseCase = get(),
            addGroupUseCase = get(),
            updateGroupUseCase = get()
        )
    }

    viewModel<MessageViewModel> {
        MessageViewModelImpl(
            getAllMessagesUseCase = get(),
            addMessageUseCase = get()
        )
    }

    viewModel<ContactsViewModel> {
        ContactsViewModelImpl(
            getAllContactsUseCase = get(),
            updateUserUseCase = get()
        )
    }
}