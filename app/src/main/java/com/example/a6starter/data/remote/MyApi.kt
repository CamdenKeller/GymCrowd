package com.example.a6starter.data.remote

import com.example.a6starter.data.model.Joke
import retrofit2.Response
import retrofit2.http.GET

interface MyApi {
    @GET("/joke/Any?safe-mode")
    suspend fun getSafeJoke(): Response<Joke>
}