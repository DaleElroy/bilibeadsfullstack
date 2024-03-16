package com.example.bilibeadsdesigns.Dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.bilibeadsdesigns.AddToCart.CartActivity
import com.example.bilibeadsdesigns.Profile.ProfilePage
import com.example.bilibeadsdesigns.R

class ViewProduct : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_product)

        // Retrieve data from the intent
        val productImage = intent.getStringExtra("product_image")
        val productTitle = intent.getStringExtra("product_title")
        val productPrice = intent.getStringExtra("product_price")

        // Set data to the views
        val imageViewProduct: ImageView = findViewById(R.id.iv_view_product)
        val textViewTitle: TextView = findViewById(R.id.tv_viewtitle)
        val textViewPrice: TextView = findViewById(R.id.tv_viewprice)
        val btnAddToCart: Button = findViewById(R.id.btnAddToCart)

        val btnViewLogo: ImageView = findViewById(R.id.iv_viewlogo)
        val btnViewCart: ImageView = findViewById(R.id.iv_viewcart)
        val btnViewProfile: ImageView = findViewById(R.id.iv_viewprofile)

        btnViewLogo.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)

        }

        btnViewCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)

        }

        btnViewProfile.setOnClickListener {
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)

        }


        Log.d("ViewProduct", "Product Image: $productImage")

        // Load image using Glide
        Glide.with(this)
            .load(productImage)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.e("ViewProduct", "Glide image loading failed: $e")
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("ViewProduct", "Glide image loading successful")
                    return false
                }
            })
            .into(imageViewProduct)

        textViewTitle.text = productTitle
        textViewPrice.text = productPrice

        btnAddToCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java).apply {
                putExtra("product_image", productImage)
                putExtra("product_title", productTitle)
                putExtra("product_price", productPrice)
            }
            startActivity(intent)
        }

    }

    // Helper function to parse the price string and convert it to Double
    private fun parsePrice(price: String?): Double {
        return price?.replace("[^0-9.]".toRegex(), "")?.toDoubleOrNull() ?: 0.0
    }
}
