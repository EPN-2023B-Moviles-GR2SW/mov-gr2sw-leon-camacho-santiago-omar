package com.example.proyecto2grupo12.data.repository

import com.example.proyecto2grupo12.data.datasource.FirestoreRemoteDataSource
import com.example.proyecto2grupo12.data.datasource.StorageRemoteDataSource
import com.example.proyecto2grupo12.data.datasource.model.FirestoreUser
import com.example.proyecto2grupo12.domain.profilecard.ProfileCardRepository
import com.example.proyecto2grupo12.domain.profilecard.entity.CurrentProfile
import com.example.proyecto2grupo12.domain.profilecard.entity.NewMatch
import com.example.proyecto2grupo12.domain.profilecard.entity.Profile
import com.example.proyecto2grupo12.domain.profilecard.entity.ProfileList
import com.example.proyecto2grupo12.extensions.toCurrentProfile
import com.example.proyecto2grupo12.extensions.toProfile
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class ProfileCardRepositoryImpl(
    private val storageDataSource: StorageRemoteDataSource,
    private val firestoreDataSource: FirestoreRemoteDataSource
): ProfileCardRepository {


    override suspend fun swipeUser(profile: Profile, isLike: Boolean): NewMatch? {
        val matchModel = firestoreDataSource.swipeUser(profile.id, isLike)
        return matchModel?.let { model ->
            NewMatch(model.id, profile.id, profile.name, profile.pictures)
        }
    }

    override suspend fun getProfiles(): ProfileList {
        val userList = firestoreDataSource.getUserList()
        //Fetch profiles
        val profileList = coroutineScope {
            val profiles = userList.compatibleUsers.map { async { getProfile(it) } }
            val currentProfile = async { getCurrentProfile(userList.currentUser) }
            ProfileList(currentProfile.await(), profiles.awaitAll())
        }
        return profileList
    }

    private suspend fun getCurrentProfile(userModel: FirestoreUser): CurrentProfile {
        val pictures =
            if (userModel.pictures.isEmpty()) emptyList() else storageDataSource.getPicturesFromUser(
                userModel.id,
                userModel.pictures
            )
        return userModel.toCurrentProfile(pictures)
    }

    private suspend fun getProfile(userModel: FirestoreUser): Profile {
        val pictures =
            if (userModel.pictures.isEmpty()) emptyList() else storageDataSource.getPicturesFromUser(
                userModel.id,
                userModel.pictures
            )
        return userModel.toProfile(pictures.map { it.uri })
    }

}