package com.example.proyecto2grupo12.data.datasource.model

data class FirestoreUserList(
    val currentUser: FirestoreUser,
    val compatibleUsers: List<FirestoreUser>)