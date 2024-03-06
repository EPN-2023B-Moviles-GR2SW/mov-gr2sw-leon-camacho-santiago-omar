package com.example.proyecto2grupo12.domain.profile

import com.example.proyecto2grupo12.domain.profile.entity.CreateUserProfile
import com.example.proyecto2grupo12.domain.profilecard.entity.CurrentProfile
import com.example.proyecto2grupo12.domain.profile.entity.UserPicture

interface ProfileRepository {
    suspend fun createUserProfile(profile: CreateUserProfile)
    suspend fun createUserProfile(userId: String, profile: CreateUserProfile)
    suspend fun updateProfile(currentProfile: CurrentProfile, newBio: String, newGenderIndex: Int, newOrientationIndex: Int, newPictures: List<UserPicture>): CurrentProfile
}