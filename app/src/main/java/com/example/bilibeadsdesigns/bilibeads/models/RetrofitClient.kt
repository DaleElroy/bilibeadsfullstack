package com.example.bilibeadsdesigns.bilibeads.models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://bilibead-bilibead-com.preview-domain.com/api/"

//    fun getService(): ApiService {
//        val gson = GsonBuilder()
//            .setLenient()
//            .create()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//        return retrofit.create(ApiService::class.java)

    fun getService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}
