package com.example.bilibeadsdesigns.Profile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bilibeadsdesigns.AddToCart.CartActivity
import com.example.bilibeadsdesigns.Dashboard.Dashboard
import com.example.bilibeadsdesigns.HelpCenter.HelpCenter
import com.example.bilibeadsdesigns.LoginActivity
import com.example.bilibeadsdesigns.R
import com.example.bilibeadsdesigns.bilibeads.models.RetrofitClient
import com.example.bilibeadsdesigns.bilibeads.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilePage : AppCompatActivity() {

    private lateinit var logoutButton: Button
    private lateinit var editProfile: Button
    private lateinit var changePass: Button
    private lateinit var tvUser: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvAddress: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_profile)

        logoutButton = findViewById(R.id.bt_logout)
        editProfile = findViewById(R.id.edit_profile)
        changePass = findViewById(R.id.change_pass)
        tvUser = findViewById(R.id.tvUser)
        tvEmail = findViewById(R.id.tvEmail)
        tvAddress = findViewById(R.id.tvAddress)

        loadUserData()

        logoutButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "LogOut", Toast.LENGTH_SHORT).show()
        }

        editProfile.setOnClickListener {
            Toast.makeText(this, "Successfully Change", Toast.LENGTH_SHORT).show()
        }

        changePass.setOnClickListener {
            Toast.makeText(this, "Changed", Toast.LENGTH_SHORT).show()
        }

        val dashboard = findViewById<ImageView>(R.id.iv_logo)
        dashboard.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
            finish()
        }

        val cart = findViewById<ImageView>(R.id.iv_cart)
        cart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        val helpCenter = findViewById<ImageView>(R.id.iv_profile)
        helpCenter.setOnClickListener {
            val intent = Intent(this, HelpCenter::class.java)
            startActivity(intent)
        }
    }

    private fun loadUserData() {
        val apiService = RetrofitClient.getService()

        apiService.getUserDetails().enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val userDetails = response.body()
                    userDetails?.let {
                        val user = it.name
                        val email = it.email

                        tvUser.text = "User: $user"
                        tvEmail.text = "Email: $email"
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                // Handle failure
                Toast.makeText(this@ProfilePage, "Failed to load user details", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
