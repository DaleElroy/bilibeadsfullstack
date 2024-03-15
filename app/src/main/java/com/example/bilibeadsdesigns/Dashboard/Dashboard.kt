package com.example.bilibeadsdesigns.Dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bilibeadsdesigns.AddToCart.CartActivity
import com.example.bilibeadsdesigns.Customize.Customize
import com.example.bilibeadsdesigns.Dashboard.adapter.DashboardAdapter
import com.example.bilibeadsdesigns.Profile.ProfilePage
import com.example.bilibeadsdesigns.R
import com.example.bilibeadsdesigns.bilibeads.models.ProductItem
import com.example.bilibeadsdesigns.bilibeads.models.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Dashboard : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productRecyclerviewAdapter: DashboardAdapter
    private var productList = mutableListOf<ProductItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_dashboard)

        val customizeButton = findViewById<AppCompatButton>(R.id.bt_customize)
        val profileButton = findViewById<ImageView>(R.id.iv_profile)
        val cartButton = findViewById<ImageView>(R.id.iv_cart)

        recyclerView = findViewById(R.id.rv_dashboard)
        productRecyclerviewAdapter = DashboardAdapter(this@Dashboard, productList)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = productRecyclerviewAdapter

        customizeButton.setOnClickListener {
            val intent = Intent(this, Customize::class.java)
            startActivity(intent)
            finish()

        }
        profileButton.setOnClickListener {
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)
            finish()

        }
        cartButton.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)


        }

        // Fetch product data using Retrofit
        fetchProductList()
    }

    private fun fetchProductList() {
        val apiService = RetrofitClient.getService()
        val call = apiService.getProductList()

        call.enqueue(object : Callback<List<ProductItem>> {
            override fun onResponse(
                call: Call<List<ProductItem>>,
                response: Response<List<ProductItem>>
            ) {
                if (response.isSuccessful) {
                    val products = response.body()
                    products?.let {
                        productRecyclerviewAdapter.setProductList(it)
                    }
                } else {
                    // Handle error response
                    Log.e("Dashboard", "Failed to fetch product list: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ProductItem>>, t: Throwable) {
                // Handle network failure
                Log.e("Dashboard", "Network error: ${t.message}")
            }
        })
    }
}
