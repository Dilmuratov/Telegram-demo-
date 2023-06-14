package com.example.telegramdemo.presentation.contactviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.telegramdemo.data.models.UserData
import com.example.telegramdemo.domain.contactusecases.getallcontacts.GetAllContactsUseCase
import com.example.telegramdemo.domain.contactusecases.updateuserusecase.UpdateUserUseCase

class ContactsViewModelImpl(
    private val getAllContactsUseCase: GetAllContactsUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
) : ContactsViewModel() {

    private val _getAllContactsLiveData = MutableLiveData<List<UserData>>()
    override val getAllContactsLiveData: LiveData<List<UserData>>
        get() = _getAllContactsLiveData

    private val _getUsernameLiveData = MutableLiveData<String>()
    override val getUsernameLiveData: LiveData<String>
        get() = _getUsernameLiveData

    override suspend fun getAllContacts() {
        getAllContactsUseCase.execute().collect() {
            _getAllContactsLiveData.value = it
        }
    }

    override suspend fun updateUser(userData: UserData) {
        updateUserUseCase.execute(userData)
    }
}