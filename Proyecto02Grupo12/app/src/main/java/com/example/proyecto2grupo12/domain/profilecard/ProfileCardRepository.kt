package com.example.proyecto2grupo12.domain.profilecard

import com.example.proyecto2grupo12.domain.profilecard.entity.NewMatch
import com.example.proyecto2grupo12.domain.profilecard.entity.Profile
import com.example.proyecto2grupo12.domain.profilecard.entity.ProfileList

interface ProfileCardRepository {
    suspend fun swipeUser(profile: Profile, isLike: Boolean): NewMatch?
    suspend fun getProfiles(): ProfileList
}