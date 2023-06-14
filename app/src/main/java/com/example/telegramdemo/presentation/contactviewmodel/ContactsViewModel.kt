package com.example.telegramdemo.presentation.contactviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.telegramdemo.data.models.UserData
import kotlinx.coroutines.flow.Flow

abstract class ContactsViewModel : ViewModel() {

    abstract val getAllContactsLiveData: LiveData<List<UserData>>
    abstract val getUsernameLiveData: LiveData<String>

    abstract suspend fun getAllContacts()

    abstract suspend fun updateUser(userData: UserData)

}