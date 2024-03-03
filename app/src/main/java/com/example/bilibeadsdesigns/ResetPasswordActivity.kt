package com.example.bilibeadsdesigns

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bilibeadsdesigns.bilibeads.models.ResetPasswordRequest
import com.example.bilibeadsdesigns.bilibeads.models.ResetPasswordResponse
import com.example.bilibeadsdesigns.bilibeads.models.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var emailEt: EditText
    private lateinit var resetPasswordBt: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        emailEt = findViewById(R.id.reset_email)
        resetPasswordBt = findViewById(R.id.bt_email_reset)

        resetPasswordBt.setOnClickListener {
            val email = emailEt.text.toString().trim()

            if (email.isNotEmpty()) {
                resetPassword(email)
            } else {
                showToast("Please enter your email.")
            }
        }
    }

    private fun resetPassword(email: String) {
        val apiService = RetrofitClient.getService(this)

        val resetPasswordRequest = ResetPasswordRequest(email)

        // Show a loading spinner or progress bar here

        apiService.resetPassword(resetPasswordRequest).enqueue(object : Callback<ResetPasswordResponse> {
            override fun onResponse(call: Call<ResetPasswordResponse>, response: Response<ResetPasswordResponse>) {
                // Hide the loading spinner or progress bar here

                if (response.isSuccessful) {
                    val resetPasswordResponse = response.body()
                    resetPasswordResponse?.let {
                        showToast("Password reset successful")
                        // Optionally, navigate to another screen (e.g., login)
                        startActivity(Intent(this@ResetPasswordActivity, LoginActivity::class.java))
                        finish()
                    }
                } else {
                    showToast("Password reset failed. Please try again.")
                    // Handle specific error scenarios if needed
                }
            }

            override fun onFailure(call: Call<ResetPasswordResponse>, t: Throwable) {
                // Hide the loading spinner or progress bar here

                showToast("Password reset failed. Please try again.")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
