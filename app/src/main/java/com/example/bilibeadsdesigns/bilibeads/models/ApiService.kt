package com.example.bilibeadsdesigns.bilibeads.models

import com.example.bilibeadsdesigns.bilibeads.models.LoginUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("user")
    fun getUser(): Call<List<User>>
    @POST("register")
    fun register(@Body user: User):Call<User>
    @POST("login")
    fun login(@Body credentials: User): Call<User>

    @GET("user")
    fun getUserDetails(): Call<User>

    @POST("reset-password")
    fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest): Call<ResetPasswordResponse>

}