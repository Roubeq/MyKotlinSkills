package com.example.koin

import com.example.koin.models.CountriesItem

class MyRepositoryImpl(
    private val myApiService: ApiService
) : MyRepository {
    override suspend fun fetchCountries(): List<CountriesItem> {
        return myApiService.getCountries()
    }
}