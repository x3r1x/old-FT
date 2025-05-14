package com.example.fitnesstogether.data

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("userName") // Имя поля на сервере
    val userName: String,
    @SerializedName("password") // Имя поля на сервере
    val password: String
)