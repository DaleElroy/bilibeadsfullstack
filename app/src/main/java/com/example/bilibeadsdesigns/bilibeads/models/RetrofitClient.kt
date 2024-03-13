package com.example.bilibeadsdesigns.bilibeads.models

import android.content.Context
import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://bilibeadss-bilibeadss-com.preview-domain.com/api/"
    private const val PREF_NAME = "mySharedPreferences"
    private const val TOKEN_KEY = "token"

//    fun saveToken(context: Context, token: String) {
//        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
//        prefs.edit().putString(TOKEN_KEY, token).apply()
//        Log.d("RetrofitClient", "Token saved: $token")
//    }
//
//    private fun getToken(context: Context): String? {
//        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
//        return prefs.getString(TOKEN_KEY, null)
//    }
//
//    private class AuthInterceptor(val context: Context) : Interceptor {
//        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
//            val token = getToken(context)
//            val request = chain.request().newBuilder()
//            token?.let {
//                request.addHeader("Authorization", "Bearer $token")
//            }
//            return chain.proceed(request.build())
//        }
//    }
//
//    fun getService(context: Context): ApiService {
//        val loggingInterceptor = HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        }
//        val authInterceptor = AuthInterceptor(context)
//
//        val client = OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .addInterceptor(authInterceptor)
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//
//        return retrofit.create(ApiService::class.java)
//    }
//}

    fun getService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }
    fun saveToken(context: Context, token: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(TOKEN_KEY, token).apply()
    }

    fun getToken(context: Context): String? {
        val prefs: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(TOKEN_KEY, null)
    }

    private class AuthInterceptor(val context: Context) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val token = getToken(context)
            val request = chain.request().newBuilder()

            token?.let {
                request.addHeader("Authorization", "Bearer $token")
            }
            return chain.proceed(request.build())
        }
    }

    fun logout(context: Context) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }
}
