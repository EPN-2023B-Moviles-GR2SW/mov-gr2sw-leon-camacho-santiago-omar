package com.example.proyecto2grupo12.extensions

fun String.isValidUsername(): Boolean{
    return this.length in 3..30 && this.all { it.isLetter() }
}