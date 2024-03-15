package com.example.bilibeadsdesigns.Customize

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bilibeadsdesigns.AddToCart.CartActivity
import com.example.bilibeadsdesigns.Dashboard.Dashboard
import com.example.bilibeadsdesigns.Profile.ProfilePage
import com.example.bilibeadsdesigns.R

class Customize : AppCompatActivity() {
    private var currentImageViewIndex = 0
    private lateinit var imageViewArray: Array<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_customize)

        imageViewArray = Array(15) {
            findViewById(resources.getIdentifier("viewImage${it + 1}", "id", packageName))
        }

        val backButton = findViewById<ImageView>(R.id.iv_logo)
        backButton.setOnClickListener{
            val intent = Intent (this, Dashboard::class.java)
            startActivity(intent)

        }

        val shoppingCart = findViewById<ImageView>(R.id.iv_cart)
        shoppingCart.setOnClickListener {
            val intent = Intent(this,CartActivity::class.java)
            startActivity(intent)

        }

        val profile = findViewById<ImageView>(R.id.iv_profile)
        profile.setOnClickListener {
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)

        }

        val recyclerView = findViewById<RecyclerView>(R.id.rv_productList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val layoutManager:RecyclerView.LayoutManager = GridLayoutManager(this, 4)
        recyclerView!!.layoutManager = layoutManager

        val itemList = listOf(
            DataClassCustomize(R.drawable.bead1),
            DataClassCustomize(R.drawable.bead2),
            DataClassCustomize(R.drawable.bead3),
            DataClassCustomize(R.drawable.bead4),
            DataClassCustomize(R.drawable.bead5),
            DataClassCustomize(R.drawable.bead6),
            DataClassCustomize(R.drawable.bead7),
            DataClassCustomize(R.drawable.bead8)
        )

        val adapter = CustomizeAdapter(itemList) { position ->
            if (currentImageViewIndex < imageViewArray.size) {
                imageViewArray[currentImageViewIndex].setImageResource(itemList[position].imageResource)
                currentImageViewIndex++
            }
        }

        recyclerView.adapter = adapter

        val resetButton: Button = findViewById(R.id.reset_button)
        resetButton.setOnClickListener {
            resetImageViews()
        }

        val clearButton: Button = findViewById(R.id.clear_button)
        clearButton.setOnClickListener {
            clearImageView()
        }

    }

    fun clearImageView() {
        if (currentImageViewIndex > 0) {
            currentImageViewIndex--
            imageViewArray[currentImageViewIndex].setImageResource(0)
        }

    }

    fun resetImageViews() {
        for (imageView in imageViewArray) {
            imageView.setImageResource(R.drawable.card_view_color)
        }
        currentImageViewIndex = 0
    }

}