package com.example.bilibeadsdesigns

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.bilibeadsdesigns.Dashboard.Dashboard
import com.example.bilibeadsdesigns.bilibeads.models.RetrofitClient
import com.example.bilibeadsdesigns.bilibeads.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationActivity : AppCompatActivity() {

    private lateinit var et_name: EditText
    private lateinit var et_emailAddress: EditText
    private lateinit var et_password: EditText
    private lateinit var et_confirmPassword: EditText
    private lateinit var tv_button_SignIn: AppCompatButton
    private lateinit var tv_button_Login: TextView

    private val apiService = RetrofitClient.getService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_registration)

        et_name = findViewById(R.id.register_username)
        et_emailAddress = findViewById(R.id.register_email)
        et_password = findViewById(R.id.register_password)
        et_confirmPassword = findViewById(R.id.register_confirm_password)
        tv_button_SignIn = findViewById(R.id.bt_registration)
        tv_button_Login = findViewById(R.id.tv_button_SignIn)

        tv_button_Login.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        tv_button_SignIn.setOnClickListener {
            registerUser()
        }
    }



    private fun registerUser() {
        val name = et_name.text.toString().trim()
        val email = et_emailAddress.text.toString().trim()
        val password = et_password.text.toString().trim()
        val confirmPassword = et_confirmPassword.text.toString().trim()

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(
                this@RegistrationActivity,
                "Please fill in all fields",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (password != confirmPassword) {
            Toast.makeText(
                this@RegistrationActivity,
                "Passwords do not match",
                Toast.LENGTH_SHORT
            ).show()
            et_password.text.clear()
            et_confirmPassword.text.clear()
            return
        }
        val user = User(
            name = name,
            email = email,
            password = password
        )
        val call = apiService.register(user)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val registeredUser = response.body()
                    saveUserData(registeredUser)

                    startActivity(Intent(this@RegistrationActivity, Dashboard::class.java))
                    finish()
                    Toast.makeText(
                        this@RegistrationActivity,
                        "Registered successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    Toast.makeText(
                        this@RegistrationActivity,
                        "Registration failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(
                    this@RegistrationActivity,
                    "User Already Exist!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun saveUserData(user: User?) {
        val sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("name", user?.name)
        editor.putString("email", user?.email)
        // Add more fields as needed

        editor.apply()
    }
}
