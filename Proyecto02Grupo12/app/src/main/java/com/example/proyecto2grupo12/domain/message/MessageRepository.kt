package com.example.proyecto2grupo12.domain.message

import com.example.proyecto2grupo12.domain.message.entity.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getMessages(matchId: String): Flow<List<Message>>
    suspend fun sendMessage(matchId: String, text: String)
}