package com.example.bilibeadsdesigns.bilibeads.models

import com.google.gson.annotations.SerializedName

data class ChangePasswordResponse(
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: String
)

