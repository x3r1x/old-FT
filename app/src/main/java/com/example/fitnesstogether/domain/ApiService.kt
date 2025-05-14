package com.example.fitnesstogether.domain

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/User/Login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}