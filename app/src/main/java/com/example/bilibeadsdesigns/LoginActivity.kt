    package com.example.bilibeadsdesigns

    import android.annotation.SuppressLint
    import android.content.Intent
    import android.os.Bundle
    import android.widget.Button
    import android.widget.EditText
    import android.widget.RelativeLayout
    import android.widget.TextView
    import android.widget.Toast
    import androidx.appcompat.app.AppCompatActivity
    import com.example.bilibeadsdesigns.Dashboard.Dashboard
    import com.example.bilibeadsdesigns.bilibeads.models.RetrofitClient
    import com.example.bilibeadsdesigns.bilibeads.models.User
    import com.example.bilibeadsdesigns.bilibeads.models.UserProfile
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

        @SuppressLint("MissingInflatedId", "ResourceType")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_page_login)

            createAccount = findViewById(R.id.create_account)
            usernameEt = findViewById(R.id.login_username)
            passwordEt = findViewById(R.id.login_password)
            loginBt = findViewById(R.id.bt_confirm_login)
            forgot = findViewById(R.id.forgot_password)

            createAccount.setOnClickListener {
                val intent = Intent(this, RegistrationActivity::class.java)
                startActivity(intent)
            }

            forgot.setOnClickListener {
                val intent = Intent(this, ResetPasswordActivity::class.java)
                startActivity(intent)
            }

            loginBt.setOnClickListener {
                val username = usernameEt.text.toString().trim()
                val password = passwordEt.text.toString().trim()

                loginUser(username, password)
            }
        }

        private fun loginUser(email: String, password: String) {
            val apiService = RetrofitClient.getService(this)

            val user = User(name = "", email = email, password = password)

            val call = apiService.login(user)

            call.enqueue(object : Callback<UserProfile> {
                override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                    if (response.isSuccessful) {
                        val loggedInUserProfile = response.body()
                        saveUserData(loggedInUserProfile)

                        Toast.makeText(applicationContext, "Login successful", Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(this@LoginActivity, Dashboard::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Login failed. Please try again.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Network error. Please try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }

        private fun saveUserData(userProfile: UserProfile?) {
            val sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.putString("id", userProfile?.id)
            editor.putString("name", userProfile?.name)
            editor.putString("email", userProfile?.email)
            editor.putString("address", userProfile?.address)
            editor.putString("password", userProfile?.password)

            editor.apply()
        }
    }


