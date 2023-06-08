package com.example.telegramdemo.data.repository

import com.example.telegramdemo.data.models.ChatData
import com.example.telegramdemo.domain.GroupsRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class GroupsRepositoryImpl(db: FirebaseFirestore) : GroupsRepository {

    private val getAllGroupsCollectionRef = db.collection("groups")

    override suspend fun getAllGroups(): Flow<List<ChatData>> = flow {
        emit(getAllGroupsCollectionRef.get().await().documents.mapNotNull {
            ChatData(
                groupId = it.id,
                groupPath = it["groupPath"].toString(),
                groupName = it["groupName"].toString(),
                lastSms = it["lastSms"].toString()
            )
        })
    }.catch { it.printStackTrace() }

    override suspend fun addGroup(chatData: ChatData) {
        getAllGroupsCollectionRef.add(chatData)
    }

    override suspend fun updateGroup(chatData: ChatData) {
        getAllGroupsCollectionRef.document(chatData.groupId).update(
            mapOf(
                "groupId" to chatData.groupId,
                "groupName" to chatData.groupName,
                "groupPath" to chatData.groupPath,
                "lastSms" to chatData.lastSms
            )
        )
    }
}