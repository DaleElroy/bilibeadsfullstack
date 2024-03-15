package com.example.bilibeadsdesigns.bilibeads.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("userId")
    val userId: String,

    @SerializedName("token")
    val token: String

)

