package com.example.a6starter.data.model

import android.util.Log
import com.example.a6starter.data.remote.MyApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyRepository @Inject constructor(
    private val myApi: MyApi,
) {
    init {
        Log.d("TAG", "my api: $myApi")
    }
}