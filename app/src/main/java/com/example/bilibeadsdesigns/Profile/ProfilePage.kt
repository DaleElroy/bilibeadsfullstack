package com.example.bilibeadsdesigns.Profile

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bilibeadsdesigns.AddToCart.CartActivity
import com.example.bilibeadsdesigns.Dashboard.Dashboard
import com.example.bilibeadsdesigns.LoginActivity
import com.example.bilibeadsdesigns.R
import com.example.bilibeadsdesigns.UpdatePassword
import com.example.bilibeadsdesigns.bilibeads.models.UserProfile

class ProfilePage : AppCompatActivity() {

    private lateinit var logoutButton: Button
    private lateinit var editProfile: Button
    private lateinit var changePass: Button
    private lateinit var genderSpinner: Spinner

    private lateinit var tvYourName: TextView
    private lateinit var tvYourEmail: TextView
    private lateinit var tvYourAddress: TextView
    private lateinit var tvYourAge: TextView
    private lateinit var tvYourPhone: TextView
    private lateinit var tvYourGender: TextView // Changed from Spinner to TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_profile)

        val btnViewLogo: ImageView = findViewById(R.id.iv_logo)
        val btnViewCart: ImageView = findViewById(R.id.iv_cart)

        btnViewLogo.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)

        }

        btnViewCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)

        }


        tvYourName = findViewById(R.id.tvName)
        tvYourEmail = findViewById(R.id.tvEmail)
        tvYourAddress = findViewById(R.id.tvAddress)
        tvYourAge = findViewById(R.id.tvAge)
        tvYourPhone = findViewById(R.id.tvNumber)
//        tvYourGender = findViewById(R.id.spinnerGender) // Initialize TextView for gender

        // Assuming you have already retrieved the user data from SharedPreferences
        val userData = getUserData()

        // Add the following lines to initialize the spinner
        genderSpinner = findViewById(R.id.spinnerGender)
        val genderAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.gender_options,
            android.R.layout.simple_spinner_item
        )
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        genderSpinner.adapter = genderAdapter
        // End of spinner initialization

        userData?.let { user ->
            tvYourName.text = "Name: ${user.name}"
            tvYourEmail.text = "Email: ${user.email}"
            tvYourAddress.text = "Address: ${user.address}"
            tvYourAge.text = "Age: ${user.age}"
            tvYourPhone.text = "Phone: ${user.phone}"
            tvYourGender.text = "Gender: ${user.gender}" // Set text for gender TextView

            // Set the selected gender in the spinner based on user data
            val genderPosition = genderAdapter.getPosition(user.gender)
            genderSpinner.setSelection(genderPosition)
        }

        logoutButton = findViewById(R.id.bt_logout)
//        editProfile = findViewById(R.id.edit_profile)
        changePass = findViewById(R.id.change_pass)

        // Insert the code below
        val updateMessage = intent.getStringExtra("update_message")
        if (!updateMessage.isNullOrBlank()) {
            Toast.makeText(this, updateMessage, Toast.LENGTH_SHORT).show()
        }
        // End of inserted code

        logoutButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "LogOut", Toast.LENGTH_SHORT).show()
        }

//        editProfile.setOnClickListener {
//            // Get the selected gender from the spinner
//            val selectedGender = genderSpinner.selectedItem.toString()
//
//            // Add logic to handle the selected gender (optional)
//
//            Toast.makeText(this, "Successfully Change", Toast.LENGTH_SHORT).show()
//            // Add logic to navigate to the edit profile page
//        }

        changePass.setOnClickListener {
            // Add logic to navigate to the UpdatePassword activity
            val intent = Intent(this, UpdatePassword::class.java)
            startActivity(intent)
        }
    }

    // Replace UserProfile with your actual user profile model class
    private fun getUserData(): UserProfile? {
        val sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        val id = sharedPreferences.getString("id", null)
        val name = sharedPreferences.getString("name", null)
        val email = sharedPreferences.getString("email", null)
        val address = sharedPreferences.getString("address", null)
        val password = sharedPreferences.getString("password", null)
        val age = sharedPreferences.getString("age", null)
        val phone = sharedPreferences.getString("phone", null)
        val gender = sharedPreferences.getString("gender", null)

        return if (id != null && name != null && email != null && address != null && password != null &&
            age != null && phone != null && gender != null
        ) {
            UserProfile(id = id, name = name, email = email, address = address, password = password, age = age, phone = phone, gender = gender)
        } else {
            null
        }
    }
}








//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.bilibeadsdesigns.LoginActivity
//import com.example.bilibeadsdesigns.R
//import com.example.bilibeadsdesigns.bilibeads.models.RetrofitClient
//import com.example.bilibeadsdesigns.bilibeads.models.UserProfile
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class ProfilePage : AppCompatActivity() {
//
//    private lateinit var logoutButton: Button
//    private lateinit var editProfile: Button
//    private lateinit var changePass: Button
//    private lateinit var tvYourName: TextView
//    private lateinit var tvYourEmail: TextView
//    private lateinit var tvYourAddress: TextView // Add this TextView for address
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_page_profile)
//
//        tvYourName = findViewById(R.id.tvUser)
//        tvYourEmail = findViewById(R.id.tvEmail)
//        tvYourAddress = findViewById(R.id.tvAddress)
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
//
//        // Fetch user profile data from the API
//        fetchUserProfileData()
//
//        // ... (existing code)
//    }
//
//    private fun fetchUserProfileData() {
//        val apiService = RetrofitClient.getService(this)
//        val call = apiService.getUserProfile()
//
//        call.enqueue(object : Callback<UserProfile> {
//            override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
//                if (response.isSuccessful) {
//                    val userProfile = response.body()
//
//                    userProfile?.let {
//                        // Update UI with fetched user profile data
//                        tvYourName.text = it.name
//                        tvYourEmail.text = it.email
//                        tvYourAddress.text = it.address
//                    }
//                } else {
//                    // Handle error response
//                    Toast.makeText(this@ProfilePage, "Failed to fetch user profile", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
//                // Handle network failure
//                Toast.makeText(this@ProfilePage, "Network error", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
//
//    // ... (existing code)
//}
