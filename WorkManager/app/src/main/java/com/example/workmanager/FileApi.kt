package com.example.workmanager

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface FileApi {

    @GET("/736x/d0/68/8a/d0688aa78b638f51e9e2f626e203ae27.jpg")
    suspend fun getDownloadPhoto() : Response<ResponseBody>

    companion object {
        val instance by lazy {
            Retrofit.Builder()
                .baseUrl("https://i.pinimg.com")
                .build()
                .create(FileApi::class.java)
        }
    }
}