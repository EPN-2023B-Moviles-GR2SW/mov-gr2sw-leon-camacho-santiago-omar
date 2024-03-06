package com.example.proyecto2grupo12.data.di

import com.example.proyecto2grupo12.data.datasource.AuthRemoteDataSource
import com.example.proyecto2grupo12.data.datasource.FirestoreRemoteDataSource
import com.example.proyecto2grupo12.data.datasource.StorageRemoteDataSource
import com.example.proyecto2grupo12.data.repository.*
import com.example.proyecto2grupo12.domain.auth.AuthRepository
import com.example.proyecto2grupo12.domain.match.MatchRepository
import com.example.proyecto2grupo12.domain.message.MessageRepository
import com.example.proyecto2grupo12.domain.profile.ProfileRepository
import com.example.proyecto2grupo12.domain.profilecard.ProfileCardRepository
import org.koin.dsl.module

val dataModule = module {

    //Data sources
    single { AuthRemoteDataSource() }
    single { FirestoreRemoteDataSource() }
    single { StorageRemoteDataSource() }

    //Repositories
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<MatchRepository> { MatchRepositoryImpl(get(),get(),get()) }
    single<MessageRepository> { MessageRepositoryImpl(get()) }
    single<ProfileCardRepository> { ProfileCardRepositoryImpl(get(), get()) }
    single<ProfileRepository> { ProfileRepositoryImpl(get(),get(),get()) }
}