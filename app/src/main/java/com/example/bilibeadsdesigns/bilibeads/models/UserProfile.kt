package com.example.bilibeadsdesigns.bilibeads.models

data class UserProfile(
    val id: String? = null,
    val name: String,
    val email: String,
    val address: String, // Add other fields as needed
    val password: String
)
