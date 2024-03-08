package com.example.bilibeadsdesigns.bilibeads.models

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
//    @GET("user")
//    fun getUserData(): Call<List<User>>
    @POST("register")
    fun register(@Body user: User):Call<User>
    @POST("login")
    fun login(@Body credentials: User): Call<User>

//    @GET("user/{id}")
//    fun getUserDetails(@Path("id") id: String): Call<User>

    @POST("reset-password")
    fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest): Call<ResetPasswordResponse>

    @GET("product")
    fun getProductList(): Call<List<ProductItem>> ///ito yon sa product

    @GET("user_details")
    fun getUserProfile(): Call<UserProfile>

}