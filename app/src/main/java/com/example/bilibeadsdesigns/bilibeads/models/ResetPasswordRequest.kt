package com.example.bilibeadsdesigns.bilibeads.models


import com.google.gson.annotations.SerializedName

data class ResetPasswordRequest(
    @SerializedName("email")
    val email: String
)

