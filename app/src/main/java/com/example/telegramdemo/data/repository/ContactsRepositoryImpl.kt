package com.example.telegramdemo.data.repository

import com.example.telegramdemo.data.models.UserData
import com.example.telegramdemo.domain.ContactsRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class ContactsRepositoryImpl(private val db: FirebaseFirestore) : ContactsRepository {

    private val getAllContactsCollectionRef = db.collection("users")

    override suspend fun getAllContacts(): Flow<List<UserData>> = flow {
        emit(getAllContactsCollectionRef.get().await().documents.mapNotNull {
            UserData(
                userId = it.id,
                userName = it["userName"].toString(),
                userSurname = it["userSurname"].toString(),
                phoneNumber = it["phoneNumber"].toString()
            )
        })
    }.catch { it.printStackTrace() }

    override suspend fun updateUser(userData: UserData) {
        getAllContactsCollectionRef.document(userData.userId).update(
            mapOf(
                "phoneNumber" to userData.phoneNumber,
                "userId" to userData.userId,
                "userName" to userData.userName,
                "userSurname" to userData.userSurname
            )
        )
    }
}