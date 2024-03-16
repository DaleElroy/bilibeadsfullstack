package com.example.bilibeadsdesigns.bilibeads.models


data class CartResponse(
    val success: Boolean,
    val carts: List<ProductCart>
)
