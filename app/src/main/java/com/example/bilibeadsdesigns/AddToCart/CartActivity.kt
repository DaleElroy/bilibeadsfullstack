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

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bilibeadsdesigns.R
import com.example.bilibeadsdesigns.bilibeads.models.ProductCart
import com.example.bilibeadsdesigns.bilibeads.models.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartActivity : AppCompatActivity() {
    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartItems: MutableList<ProductCart>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_shopping_cart)

        val cartRecyclerView = findViewById<RecyclerView>(R.id.rv_cart)
        cartItems = mutableListOf()
        cartAdapter = CartAdapter(this, cartItems)
        cartRecyclerView.layoutManager = LinearLayoutManager(this)
        cartRecyclerView.adapter = cartAdapter

        fetchCartItems()
    }

    private fun fetchCartItems() {
        val service = RetrofitClient.getService()
        val call = service.getCartItems()

        call.enqueue(object : Callback<List<ProductCart>> {
            override fun onResponse(call: Call<List<ProductCart>>, response: Response<List<ProductCart>>) {
                if (response.isSuccessful) {
                    val items = response.body()
                    items?.let {
                        cartAdapter.setItems(it)
                    }
                } else {
                    Toast.makeText(this@CartActivity, "Failed to fetch cart items: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ProductCart>>, t: Throwable) {
                val errorMessage = "Error fetching cart items: ${t.message}"
                Toast.makeText(this@CartActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}



