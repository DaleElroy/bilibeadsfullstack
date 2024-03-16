//package com.example.bilibeadsdesigns.AddToCart
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.bilibeadsdesigns.R
//import com.example.bilibeadsdesigns.bilibeads.models.ProductCart
//
//class CartActivity : AppCompatActivity() {
//    @SuppressLint("WrongViewCast", "MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_page_shopping_cart)
//
//        val cartItems = Cart.getCartItems()
//
//        val cartRecyclerView = findViewById<RecyclerView>(R.id.rv_cart)
//        val cartAdapter = CartAdapter(cartItems as MutableList<ProductCart>, Cart)
//        cartRecyclerView.layoutManager = LinearLayoutManager(this)
//        cartRecyclerView.adapter = cartAdapter
//    }
//}
// CartActivity.kt
package com.example.bilibeadsdesigns.AddToCart

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bilibeadsdesigns.R
import com.example.bilibeadsdesigns.bilibeads.models.CartResponse
import com.example.bilibeadsdesigns.bilibeads.models.ProductCart
import com.example.bilibeadsdesigns.bilibeads.models.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartActivity : AppCompatActivity() {
    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartItems: MutableList<ProductCart>
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_shopping_cart)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val cartRecyclerView = findViewById<RecyclerView>(R.id.rv_cart)
        cartItems = mutableListOf()
        cartAdapter = CartAdapter(this, cartItems)
        cartRecyclerView.layoutManager = LinearLayoutManager(this)
        cartRecyclerView.adapter = cartAdapter

        fetchCartItems()
    }

    private fun fetchCartItems() {
        val token = sharedPreferences.getString("token", "") ?: ""

        Log.d("fetchCartItems", "Retrieved token: $token")

        val service = RetrofitClient.getService()
        val call = service.getCartItems("Bearer $token")

        call.enqueue(object : Callback<CartResponse> {
            override fun onResponse(
                call: Call<CartResponse>,
                response: Response<CartResponse>
            ) {
                if (response.isSuccessful) {
                    val cartResponse = response.body()
                    if (cartResponse?.success == true) {
                        val carts = cartResponse.carts
                        cartItems.clear()
                        cartItems.addAll(carts)
                        cartAdapter.notifyDataSetChanged()
                        Log.d("fetchCartItems", "Cart items fetched successfully")
                    } else {
                        val errorMessage = "Failed to fetch cart items"
                        Log.e("fetchCartItems", errorMessage)
                        Toast.makeText(this@CartActivity, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val errorMessage = "Failed to fetch cart items: ${response.code()}"
                    Log.e("fetchCartItems", errorMessage)
                    Toast.makeText(this@CartActivity, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                val errorMessage = "Error fetching cart items: ${t.message}"
                Log.e("fetchCartItems", errorMessage)
                Toast.makeText(this@CartActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}


