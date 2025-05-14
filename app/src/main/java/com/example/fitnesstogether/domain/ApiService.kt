// src/main/java/com/example/fitnesstogether/network/ApiService.kt
package com.example.fitnesstogether.network

import com.example.fitnesstogether.data.LoginRequest
import com.example.fitnesstogether.data.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/User/Login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}