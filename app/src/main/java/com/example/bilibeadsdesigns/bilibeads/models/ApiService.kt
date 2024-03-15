package com.example.bilibeadsdesigns.bilibeads.models

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("user")
    fun getUserData(): Call<List<User>>

    //    @POST("login")
//    fun login(@Body credentials: User): Call<User>

    @GET("user/{id}")
    fun getUserDetails(@Path("id") id: String): Call<User>

    @POST("register")
    fun register(@Body user: User):Call<User>

    @GET("user_details")
    fun getUserProfile(): Call<UserProfile>

    @POST("reset-password")
    fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest): Call<ResetPasswordResponse>

    @GET("product")
    fun getProductList(): Call<List<ProductItem>> ///ito yon sa product


//    @POST("login")
//    fun login(@Body user: User): Call<LoginResponse>

    @POST("login")
    fun login(@Body loginUser: LoginUser): Call<LoginResponse>

    @GET("addtocart")
    fun getCartItems(): Call<List<ProductCart>>

//    @POST("/changepassword")
//    fun changePassword(@Header("Authorization") token: String): Call<ChangePasswordResponse>

//    @POST("/changepassword")
//    @FormUrlEncoded
//    fun changePassword(@Field("password") password: String): Call<ChangePasswordResponse>

    @POST("changepass")
    @FormUrlEncoded
    fun changePassword(
        @Field("password") password: String,
        @Header("Authorization") token: String
    ): Call<ChangePasswordResponse>
}
