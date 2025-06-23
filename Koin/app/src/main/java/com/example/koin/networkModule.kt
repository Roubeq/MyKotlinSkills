package com.example.koin

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://www.apicountries.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    single<MyRepository> {
        MyRepositoryImpl(get())
    }
    // when we have two viewModels and both of them require same dependence
    // use factory
    viewModel {
        MainViewModel(get())
    }
}
