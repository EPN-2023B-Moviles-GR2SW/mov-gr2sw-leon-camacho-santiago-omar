package com.example.proyecto2grupo12.data.repository

import com.example.proyecto2grupo12.data.datasource.FirestoreRemoteDataSource
import com.example.proyecto2grupo12.domain.message.MessageRepository

class MessageRepositoryImpl(private val firestoreDataSource : FirestoreRemoteDataSource):
    MessageRepository {

    override fun getMessages(matchId: String) = firestoreDataSource.getMessages(matchId)

    override suspend fun sendMessage(matchId: String, text: String) {
        firestoreDataSource.sendMessage(matchId, text)
    }
}