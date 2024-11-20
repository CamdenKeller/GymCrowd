package com.example.a6starter.data.model

import com.example.a6starter.data.remote.MyApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyRepository @Inject constructor(
    private val myApi: MyApi,
) {
    suspend fun getSafeJoke(): Response<Joke> {
        return myApi.getSafeJoke()
    }
}