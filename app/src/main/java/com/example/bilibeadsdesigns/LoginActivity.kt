//package com.example.bilibeadsdesigns
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import android.widget.EditText
//import android.widget.RelativeLayout
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.bilibeadsdesigns.Dashboard.Dashboard
//import com.example.bilibeadsdesigns.bilibeads.models.LoginResponse
//import com.example.bilibeadsdesigns.bilibeads.models.LoginUser
//import com.example.bilibeadsdesigns.bilibeads.models.RetrofitClient
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class LoginActivity : AppCompatActivity() {
//
//    private lateinit var createAccount: RelativeLayout
//    private lateinit var usernameEt: EditText
//    private lateinit var passwordEt: EditText
//    private lateinit var loginBt: Button
//    private lateinit var username: EditText
//    private lateinit var forgot: TextView
//
//    @SuppressLint("MissingInflatedId", "ResourceType")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_page_login)
//
//        createAccount = findViewById(R.id.create_account)
//        usernameEt = findViewById(R.id.login_username)
//        passwordEt = findViewById(R.id.login_password)
//        loginBt = findViewById(R.id.bt_confirm_login)
//        forgot = findViewById(R.id.forgot_password)
//
//        createAccount.setOnClickListener {
//            val intent = Intent(this, RegistrationActivity::class.java)
//            startActivity(intent)
//        }
//
//        forgot.setOnClickListener {
//            val intent = Intent(this, ResetPasswordActivity::class.java)
//            startActivity(intent)
//        }
//
//        loginBt.setOnClickListener {
//            val email = usernameEt.text.toString().trim()
//            val password = passwordEt.text.toString().trim()
//
//            // Validate email and password length
//            val isEmailValid = isEmailValid(email)
//            val isPasswordValid = isPasswordValid(password)
//
//            if (!isEmailValid && !isPasswordValid) {
//                Toast.makeText(
//                    applicationContext,
//                    "Email and password must be at least 8 characters long.",
//                    Toast.LENGTH_SHORT
//                ).show()
//            } else if (!isEmailValid) {
//                Toast.makeText(
//                    applicationContext,
//                    "Email must be at least 8 characters long.",
//                    Toast.LENGTH_SHORT
//                ).show()
//            } else if (!isPasswordValid) {
//                Toast.makeText(
//                    applicationContext,
//                    "Password must be at least 8 characters long.",
//                    Toast.LENGTH_SHORT
//                ).show()
//            } else {
//                loginUser(email, password)
//            }
//        }
//    }
//    private fun loginUser(email: String, password: String) {
//        val call = RetrofitClient.getService().login(LoginUser(email, password))
//        call.enqueue(object : Callback<LoginResponse> {
//            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                if (response.isSuccessful) {
//                    val loginResponse = response.body()
//                    if (loginResponse != null) {
//                        val token = loginResponse.token
//                        val userId = loginResponse.userId
//
//                        // Save token and user ID
//                        saveToken(token)
//                        saveUserId(userId)
//
//                        Toast.makeText(applicationContext, "Login successful", Toast.LENGTH_SHORT)
//                        .show()
//
//                        // Navigate to the dashboard or another activity
//                        val intent = Intent(this@LoginActivity, Dashboard::class.java)
//                        startActivity(intent)
//                    } else {
//                        Toast.makeText(applicationContext, "Login response is null", Toast.LENGTH_SHORT).show()
//                    }
//                } else {
//                    // Handle unsuccessful login (e.g., invalid credentials)
//                    Toast.makeText(applicationContext, "Login failed", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                // Handle network failures or server errors
//                Toast.makeText(applicationContext, "Failed to communicate with the server", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
//
//
//    private fun saveToken(token: String) {
//        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        editor.putString("token", token)
//        Log.d("SharedPreferences", "User token saved: token=$token")
//        editor.apply()
//    }
//
//    private fun saveUserId(userId: String) {
//        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        editor.putString("userId", userId)
//        Log.d("SharedPreferences", "User Id saved: userId=$userId")
//        editor.apply()
//    }
//
//
////    private fun loginUser(email: String, password: String) {
////        val apiService = RetrofitClient.getService()
////
////        val user = User(name = "", email = email, password = password)
////
////        val call = apiService.login(user)
////
////        call.enqueue(object : Callback<UserProfile> {
////            override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
////                if (response.isSuccessful) {
////                    val loggedInUserProfile = response.body()
////                    saveUserData(loggedInUserProfile)
////
////                    Toast.makeText(applicationContext, "Login successful", Toast.LENGTH_SHORT)
////                        .show()
////                    val intent = Intent(this@LoginActivity, Dashboard::class.java)
////                    startActivity(intent)
////                } else {
////                    Toast.makeText(
////                        applicationContext,
////                        "Login failed. Please try again.",
////                        Toast.LENGTH_SHORT
////                    ).show()
////                }
////            }
////
////            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
////                Toast.makeText(
////                    applicationContext,
////                    "Network error. Please try again.",
////                    Toast.LENGTH_SHORT
////                ).show()
////            }
////        })
////    }
//
////    private fun handleSuccessfulLogin(login: LoginResponse){
////        RetrofitClient.saveToken(this@LoginActivity, login.token)
////        saveUserData(login.user)
////        val intent = Intent(this@LoginActivity, Dashboard::class.java)
////        startActivity(intent)
////        finish()
////    }
//
////    private fun saveUserData(userProfile: UserProfile?) {
////        val sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)
////        val editor = sharedPreferences.edit()
////        userProfile?.id?.let { editor.putString("id", it) }
////        userProfile?.name?.let { editor.putString("name", it) }
////        userProfile?.email?.let { editor.putString("email", it) }
////        userProfile?.password?.let { editor.putString("password", it) }
////        editor.apply()
////    }
////}
//
////    private fun saveUserData(userProfile: UserProfile?) {
////        val sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)
////        val editor = sharedPreferences.edit()
////
////        editor.putString("id", userProfile?.id)
////        editor.putString("name", userProfile?.name)
////        editor.putString("email", userProfile?.email)
////        editor.putString("address", userProfile?.address)
////        editor.putString("password", userProfile?.password)
////        editor.putString("age", userProfile?.age)
////        editor.putString("phone", userProfile?.phone)
////        editor.putString("gender", userProfile?.gender)
////
////        editor.apply()
////    }
//
//    private fun isEmailValid(email: String): Boolean {
//        return email.length >= 8
//    }
//
//    private fun isPasswordValid(password: String): Boolean {
//        return password.length >= 8
//    }
//}
//
//
//
package com.example.bilibeadsdesigns

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bilibeadsdesigns.Dashboard.Dashboard
import com.example.bilibeadsdesigns.bilibeads.models.LoginResponse
import com.example.bilibeadsdesigns.bilibeads.models.LoginUser
import com.example.bilibeadsdesigns.bilibeads.models.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var createAccount: RelativeLayout
    private lateinit var usernameEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var loginBt: Button
    private lateinit var username: EditText
    private lateinit var forgot: TextView
    private lateinit var passwordVisibilityToggle: ImageView

    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_login)

        createAccount = findViewById(R.id.create_account)
        usernameEt = findViewById(R.id.login_username)
        passwordEt = findViewById(R.id.login_password)
        loginBt = findViewById(R.id.bt_confirm_login)
        forgot = findViewById(R.id.forgot_password)
        passwordVisibilityToggle = findViewById(R.id.password_visibility_toggle)

        createAccount.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        forgot.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }

        loginBt.setOnClickListener {
            val email = usernameEt.text.toString().trim()
            val password = passwordEt.text.toString().trim()

            // Validate email and password length
            val isEmailValid = isEmailValid(email)
            val isPasswordValid = isPasswordValid(password)

            if (!isEmailValid && !isPasswordValid) {
                Toast.makeText(
                    applicationContext,
                    "Email and password must be at least 8 characters long.",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (!isEmailValid) {
                Toast.makeText(
                    applicationContext,
                    "Email must be at least 8 characters long.",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (!isPasswordValid) {
                Toast.makeText(
                    applicationContext,
                    "Password must be at least 8 characters long.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                loginUser(email, password)
            }
        }

        // Set click listener for password visibility toggle
        passwordVisibilityToggle.setOnClickListener {
            togglePasswordVisibility()
        }
    }

    private fun togglePasswordVisibility() {
        // Change the input type of password EditText based on its current input type
        if (passwordEt.transformationMethod == android.text.method.PasswordTransformationMethod.getInstance()) {
            // Password is currently hidden, so show it
            passwordEt.transformationMethod = android.text.method.HideReturnsTransformationMethod.getInstance()
            passwordVisibilityToggle.setImageResource(R.drawable.show)
        } else {
            // Password is currently shown, so hide it
            passwordEt.transformationMethod = android.text.method.PasswordTransformationMethod.getInstance()
            passwordVisibilityToggle.setImageResource(R.drawable.hide)
        }
        // Move cursor to the end of the text
        passwordEt.setSelection(passwordEt.text.length)
    }

    private fun loginUser(email: String, password: String) {
        val call = RetrofitClient.getService().login(LoginUser(email, password))
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        val token = loginResponse.token
                        val userId = loginResponse.userId

                        // Save token and user ID
                        saveToken(token)
                        saveUserId(userId)

                        Toast.makeText(applicationContext, "Login successful", Toast.LENGTH_SHORT)
                            .show()

                        // Navigate to the dashboard or another activity
                        val intent = Intent(this@LoginActivity, Dashboard::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(applicationContext, "Login response is null", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Handle unsuccessful login (e.g., invalid credentials)
                    Toast.makeText(applicationContext, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Handle network failures or server errors
                Toast.makeText(applicationContext, "Failed to communicate with the server", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun saveToken(token: String) {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        Log.d("SharedPreferences", "User token saved: token=$token")
        editor.apply()
    }

    private fun saveUserId(userId: String) {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("userId", userId)
        Log.d("SharedPreferences", "User Id saved: userId=$userId")
        editor.apply()
    }

    private fun isEmailValid(email: String): Boolean {
        return email.length >= 8
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 8
    }
}


