package com.example.proyecto2grupo12.ui.di

import com.example.proyecto2grupo12.data.di.dataModule
import org.koin.dsl.module

val appModule = module {
    includes(dataModule)
    includes(presentationModule)
}