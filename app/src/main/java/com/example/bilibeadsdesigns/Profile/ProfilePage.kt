package com.example.bilibeadsdesigns.Profile

//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.bilibeadsdesigns.LoginActivity
//import com.example.bilibeadsdesigns.R
//import com.example.bilibeadsdesigns.bilibeads.models.User
//
//class ProfilePage : AppCompatActivity() {
//
//    private lateinit var logoutButton: Button
//    private lateinit var editProfile: Button
//    private lateinit var changePass: Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_page_profile)
//
//        val userData = getUserData()
//
//        val tvYourName: TextView = findViewById(R.id.tvUser)
//        val tvYourEmail: TextView = findViewById(R.id.tvEmail)
////        val tvYourAddress: TextView = findViewById(R.id.tvAddress)
//
//        userData?.let { user ->
//            val fullName = user.name
//            tvYourName.text = fullName
//            tvYourEmail.text = user.email
//            // Set other views as needed
//        }
//
//        logoutButton = findViewById(R.id.bt_logout)
//        editProfile = findViewById(R.id.edit_profile)
//        changePass = findViewById(R.id.change_pass)
//
//        logoutButton.setOnClickListener {
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            Toast.makeText(this, "LogOut", Toast.LENGTH_SHORT).show()
//        }
//
//        editProfile.setOnClickListener {
//            Toast.makeText(this, "Successfully Change", Toast.LENGTH_SHORT).show()
//            // Add logic to navigate to the edit profile page
//        }
//
//        changePass.setOnClickListener {
//            Toast.makeText(this, "Changed", Toast.LENGTH_SHORT).show()
//            // Add logic to navigate to the change password page
//        }
//    }
//
//    private fun getUserData(): User? {
//        val sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)
//
//        val name = sharedPreferences.getString("name", null)
//        val email = sharedPreferences.getString("email", null)
//
//        return if (name != null && email != null) {
//            User(name = name, email = email, password = "")
//        } else {
//            null
//        }
//    }
//}


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bilibeadsdesigns.LoginActivity
import com.example.bilibeadsdesigns.R
import com.example.bilibeadsdesigns.bilibeads.models.RetrofitClient
import com.example.bilibeadsdesigns.bilibeads.models.UserProfile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilePage : AppCompatActivity() {

    private lateinit var logoutButton: Button
    private lateinit var editProfile: Button
    private lateinit var changePass: Button
    private lateinit var tvYourName: TextView
    private lateinit var tvYourEmail: TextView
    private lateinit var tvYourAddress: TextView // Add this TextView for address

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_profile)

        tvYourName = findViewById(R.id.tvUser)
        tvYourEmail = findViewById(R.id.tvEmail)
        tvYourAddress = findViewById(R.id.tvAddress)

        logoutButton = findViewById(R.id.bt_logout)
        editProfile = findViewById(R.id.edit_profile)
        changePass = findViewById(R.id.change_pass)

        logoutButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "LogOut", Toast.LENGTH_SHORT).show()
        }

        editProfile.setOnClickListener {
            Toast.makeText(this, "Successfully Change", Toast.LENGTH_SHORT).show()
            // Add logic to navigate to the edit profile page
        }

        changePass.setOnClickListener {
            Toast.makeText(this, "Changed", Toast.LENGTH_SHORT).show()
            // Add logic to navigate to the change password page
        }

        // Fetch user profile data from the API
        fetchUserProfileData()

        // ... (existing code)
    }

    private fun fetchUserProfileData() {
        val apiService = RetrofitClient.getService(this)
        val call = apiService.getUserProfile()

        call.enqueue(object : Callback<UserProfile> {
            override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                if (response.isSuccessful) {
                    val userProfile = response.body()

                    userProfile?.let {
                        // Update UI with fetched user profile data
                        tvYourName.text = it.name
                        tvYourEmail.text = it.email
                        tvYourAddress.text = it.address
                    }
                } else {
                    // Handle error response
                    Toast.makeText(this@ProfilePage, "Failed to fetch user profile", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                // Handle network failure
                Toast.makeText(this@ProfilePage, "Network error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // ... (existing code)
}
