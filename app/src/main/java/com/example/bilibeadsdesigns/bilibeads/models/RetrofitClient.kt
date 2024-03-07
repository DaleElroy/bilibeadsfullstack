package com.example.bilibeadsdesigns.bilibeads.models

//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//interface RetrofitClient {
//    companion object{
//        private const val BASE_URL = "https://bilibeads2-bilibeads2-com.preview-domain.com/api/"
//
//        fun getService(): ApiService{
//            val loggingInterceptor = HttpLoggingInterceptor().apply{
//                level = HttpLoggingInterceptor.Level.BODY
//            }
//            val client = OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
//                .build()
//
//            val retrofit = Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//
//            return retrofit.create(ApiService::class.java)
//
//
//
//        }
//    }
//}

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://bilibead-bilibead-com.preview-domain.com/api/"
    private const val PREF_NAME = "mySharedPreferences"
    private const val TOKEN_KEY = "token"

    fun saveToken(context: Context, token: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(TOKEN_KEY, token).apply()
    }

    private fun getToken(context: Context): String? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(TOKEN_KEY, null)
    }

    private class AuthInterceptor(val context: Context) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val token = getToken(context)
            val request = chain.request().newBuilder()
            token?.let {
                request.addHeader("Authorization", "Bearer $token")
            }
            return chain.proceed(request.build())
        }
    }

    fun getService(context: Context): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val authInterceptor = AuthInterceptor(context)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }
}