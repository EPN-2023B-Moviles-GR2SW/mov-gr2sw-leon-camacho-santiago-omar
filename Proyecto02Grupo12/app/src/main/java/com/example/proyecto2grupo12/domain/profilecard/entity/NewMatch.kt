package com.example.proyecto2grupo12.domain.profilecard.entity

import android.net.Uri

data class NewMatch(
    val id: String,
    val userId: String,
    val userName: String,
    val userPictures: List<Uri>
)
