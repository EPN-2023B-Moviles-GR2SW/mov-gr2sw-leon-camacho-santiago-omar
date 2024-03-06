package com.example.proyecto2grupo12.ui.di

import com.example.proyecto2grupo12.ui.chat.ChatViewModel
import com.example.proyecto2grupo12.ui.editprofile.EditProfileViewModel
import com.example.proyecto2grupo12.ui.home.HomeViewModel
import com.example.proyecto2grupo12.ui.login.LoginViewModel
import com.example.proyecto2grupo12.ui.matchlist.MatchListViewModel
import com.example.proyecto2grupo12.ui.newmatch.NewMatchViewModel
import com.example.proyecto2grupo12.ui.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    //View models
    viewModel { ChatViewModel(get()) }
    viewModel { NewMatchViewModel(get()) }
    viewModel { EditProfileViewModel(get(), get()) }
    viewModel { SignUpViewModel(get(), get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { MatchListViewModel(get()) }
}