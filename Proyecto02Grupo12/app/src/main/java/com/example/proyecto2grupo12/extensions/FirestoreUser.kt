package com.example.proyecto2grupo12.extensions

import android.net.Uri
import com.example.proyecto2grupo12.domain.profilecard.entity.CurrentProfile
import com.example.proyecto2grupo12.data.datasource.model.FirestoreUser
import com.example.proyecto2grupo12.domain.profilecard.entity.Profile
import com.example.proyecto2grupo12.domain.profile.entity.FirebasePicture

fun FirestoreUser.toProfile(uris: List<Uri>): Profile {
    return Profile(this.id, this.name, this.birthDate?.toAge() ?: 99, uris)
}

fun FirestoreUser.toCurrentProfile(uris: List<FirebasePicture>): CurrentProfile {
    return CurrentProfile(
        this.id,
        this.name,
        this.birthDate?.toLongString() ?: "",
        this.bio,
        this.male?.let { if(it) 0 else 1 } ?: -1,
        this.orientation?.ordinal ?: -1,
        uris
    )
}