package com.example.proyecto2grupo12.domain.profile.entity

import android.graphics.Bitmap
import java.time.LocalDate

data class CreateUserProfile(val name: String,
                             val birthdate: LocalDate,
                             val bio: String,
                             val isMale: Boolean,
                             val orientation: Orientation,
                             val pictures: List<Bitmap>
)