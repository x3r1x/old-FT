// src/main/java/com/example/fitnesstogether/data/LoginResponse.kt
package com.example.fitnesstogether.data

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String
)