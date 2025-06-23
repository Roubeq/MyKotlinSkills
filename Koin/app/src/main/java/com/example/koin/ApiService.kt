package com.example.koin

import com.example.koin.models.CountriesItem
import retrofit2.http.GET

interface ApiService {
    @GET("countries")
    suspend fun getCountries() : List<CountriesItem>
}