//package com.example.bilibeadsdesigns
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.EditText
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.AppCompatButton
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.example.bilibeadsdesigns.Profile.ProfilePage
//import com.example.bilibeadsdesigns.bilibeads.models.ChangePasswordRequest
//import com.example.bilibeadsdesigns.bilibeads.models.ChangePasswordResponse
//import com.example.bilibeadsdesigns.bilibeads.models.RetrofitClient
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class UpdatePassword : AppCompatActivity() {
//
//    private lateinit var currentPasswordEditText: EditText
//    private lateinit var newPasswordEditText: EditText
//    private lateinit var confirmPasswordEditText: EditText
//    private lateinit var saveButton: AppCompatButton
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_update_password)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        // Initialize views
//        currentPasswordEditText = findViewById(R.id.current_password)
//        newPasswordEditText = findViewById(R.id.new_password)
//        confirmPasswordEditText = findViewById(R.id.confirm_password)
//        saveButton = findViewById(R.id.bt_update)
//
//        // Set click listener for the Save button
//        saveButton.setOnClickListener {
//            handleChangePassword()
//        }
//    }
//
//    private fun handleChangePassword() {
//        val currentPassword = currentPasswordEditText.text.toString()
//        val newPassword = newPasswordEditText.text.toString()
//        val confirmPassword = confirmPasswordEditText.text.toString()
//
//        // Perform input validation here if needed
//
//        val apiService = RetrofitClient.getService()
//
//        val changePasswordRequest = ChangePasswordRequest(
//            currentPassword = currentPassword,
//            newPassword = newPassword,
//            confirmPassword = confirmPassword
//        )
//
//        apiService.changePassword(changePasswordRequest).enqueue(object : Callback<ChangePasswordResponse> {
//            override fun onResponse(call: Call<ChangePasswordResponse>, response: Response<ChangePasswordResponse>) {
//                if (response.isSuccessful) {
//                    val changePasswordResponse = response.body()
//                    // Handle successful response, show a message or navigate to another screen
//                    // You can access the response message using changePasswordResponse.message
//
//                    // Assuming 'ProfilePage' is the name of your ProfilePage activity class
//                    val intent = Intent(this@UpdatePassword, ProfilePage::class.java)
//                    intent.putExtra("update_message", "Password successfully updated!")
//                    startActivity(intent)
//                    finish() // Finish the UpdatePassword activity to prevent going back to it
//                } else {
//                    // Handle error response, show an error message
//                }
//            }
//
//            override fun onFailure(call: Call<ChangePasswordResponse>, t: Throwable) {
//                // Handle network error
//            }
//        })
//    }
//}
package com.example.bilibeadsdesigns

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bilibeadsdesigns.bilibeads.models.ApiService
import com.example.bilibeadsdesigns.bilibeads.models.ChangePasswordResponse
import com.example.bilibeadsdesigns.bilibeads.models.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdatePassword : AppCompatActivity() {

    private lateinit var currentPasswordEditText: EditText
    private lateinit var newPasswordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var saveChangesButton: Button
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_password)

        currentPasswordEditText = findViewById(R.id.current_password)
        newPasswordEditText = findViewById(R.id.new_password)
        confirmPasswordEditText = findViewById(R.id.confirm_password)
        saveChangesButton = findViewById(R.id.bt_update)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        apiService = RetrofitClient.getService()

        val userId = getUserId()

        saveChangesButton.setOnClickListener {
            val currentPassword = currentPasswordEditText.text.toString().trim()
            val newPassword = newPasswordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(applicationContext, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newPassword != confirmPassword) {
                Toast.makeText(applicationContext, "New password and confirm password do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            userId?.let {
                val token = sharedPreferences.getString("token", null)
                if (token != null) {
                    changePassword(newPassword, token)
                } else {
                    Toast.makeText(applicationContext, "Token not found. Please log in again.", Toast.LENGTH_SHORT).show()
                }
            } ?: run {
                Toast.makeText(applicationContext, "User ID not found. Please log in again.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getUserId(): String? {
        return sharedPreferences.getString("userId", null)
    }

    private fun changePassword(newPassword: String, token: String) {
        apiService.changePassword(newPassword, "Bearer $token")
            .enqueue(object : Callback<ChangePasswordResponse> {
                override fun onResponse(call: Call<ChangePasswordResponse>, response: Response<ChangePasswordResponse>) {
                    if (response.isSuccessful) {
                        val changePasswordResponse = response.body()
                        Toast.makeText(
                            this@UpdatePassword,
                            changePasswordResponse?.message ?: "Password Changed Successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@UpdatePassword,
                            "Failed to change password: ${response.code()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ChangePasswordResponse>, t: Throwable) {
                    Toast.makeText(
                        this@UpdatePassword,
                        "Error occurred while changing password: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    t.printStackTrace()
                }
            })
    }
}


