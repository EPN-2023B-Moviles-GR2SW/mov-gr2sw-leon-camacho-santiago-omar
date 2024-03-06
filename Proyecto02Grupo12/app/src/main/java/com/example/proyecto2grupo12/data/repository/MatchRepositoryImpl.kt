package com.example.proyecto2grupo12.data.repository

import com.example.proyecto2grupo12.data.datasource.AuthRemoteDataSource
import com.example.proyecto2grupo12.data.datasource.FirestoreRemoteDataSource
import com.example.proyecto2grupo12.data.datasource.StorageRemoteDataSource
import com.example.proyecto2grupo12.data.datasource.model.FirestoreMatch
import com.example.proyecto2grupo12.domain.match.MatchRepository
import com.example.proyecto2grupo12.domain.match.entity.Match
import com.example.proyecto2grupo12.extensions.*
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class MatchRepositoryImpl(
    private val authDataSource: AuthRemoteDataSource,
    private val storageDataSource: StorageRemoteDataSource,
    private val firestoreDataSource: FirestoreRemoteDataSource
): MatchRepository {


    override suspend fun getMatches(): List<Match> {
        val matchModels = firestoreDataSource.getFirestoreMatchModels()
        val matches = coroutineScope {
            matchModels.map { async { it.toMatch() } }.awaitAll()
        }
        return matches.filterNotNull()
    }

    private suspend fun FirestoreMatch.toMatch(): Match? {
        val userId = this.usersMatched.firstOrNull { it != authDataSource.userId } ?: return null
        val user = firestoreDataSource.getFirestoreUserModel(userId)
        val picture = storageDataSource.getPictureFromUser(userId, user.pictures.first())
        return Match(
            this.id,
            user.birthDate?.toAge() ?: 99,
            userId,
            user.name,
            picture.uri,
            this.timestamp?.toShortString() ?: "",
            this.lastMessage
        )
    }
}