package com.example.daggerhilt.data

import com.example.daggerhilt.data.remote.MyApi
import com.example.daggerhilt.domain.repository.MyRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val myApi: MyApi
): MyRepository {
    override suspend fun doNetworkCall() {
        TODO("Not yet implemented")
    }
}