package com.example.bilibeadsdesigns.bilibeads.models

data class LoginResponse(
    val token: String,
    val user: UserProfile
)

