package com.example.fitnesstogether.domain

import RefreshRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/User/Register")
    fun register(@Body request: RegisterRequest): Call<Void>
    @POST("/User/Login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
    @POST("/User/Refresh")
    fun refreshToken(@Body request: RefreshRequest): Call<LoginResponse>
}