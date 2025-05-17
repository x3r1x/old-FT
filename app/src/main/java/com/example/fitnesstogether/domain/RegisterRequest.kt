// domain/RegisterRequest.kt
package com.example.fitnesstogether.domain

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("userName")
    val userName: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("birthday")
    val birthday: String,

    @SerializedName("firstName")
    val firstName: String,

    @SerializedName("lastName")
    val lastName: String,

    @SerializedName("role")
    val role: String
)