package com.example.koin

import com.example.koin.models.CountriesItem

interface MyRepository {

    suspend fun fetchCountries() : List<CountriesItem>
}