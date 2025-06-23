package com.example.daggerhilt.domain.repository

interface MyRepository {
    suspend fun doNetworkCall()
}