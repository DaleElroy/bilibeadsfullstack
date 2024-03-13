//package com.example.bilibeadsdesigns.Dashboard.adapter
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.bilibeadsdesigns.R
//import com.example.bilibeadsdesigns.bilibeads.models.ProductItem
//
//
//class DashboardAdapter(private val context: Context, initialProductList: MutableList<ProductItem>) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {
//
//    private var productList = mutableListOf<ProductItem>()
//
//    init {
//        productList.addAll(initialProductList)
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val productName: TextView = itemView.findViewById(R.id.tv_title)
//        val productImage: ImageView = itemView.findViewById(R.id.iv_beads)
//        val productPrice: TextView = itemView.findViewById(R.id.tv_price)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.activity_dashboard_product_display_layout, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return productList.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val product = productList[position]
//
//        // Load image using Glide
////        Glide.with(context)
////            .load(product.gallery)
////            .placeholder(R.drawable.design1) // Placeholder while loading
////            .error(R.drawable.picture1) // Placeholder for error case
////            .into(holder.productImage)
//
//        val imagename = product.gallery
//        val imagepath = "https://bilibeadss-bilibeadss-com.preview-domain.com/products/$imagename"
//        Glide.with(this.context).load(imagepath).into(holder.productImage)
//
//        holder.productName.text = product.title
//        holder.productPrice.text = product.price
//
//        holder.itemView.setOnClickListener {
//            // Handle item click if needed
//        }
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun setProductList(products: List<ProductItem>) {
//        productList.clear()
//        productList.addAll(products)
//        notifyDataSetChanged()
//    }
//}

package com.example.bilibeadsdesigns.Dashboard.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bilibeadsdesigns.Dashboard.ViewProduct
import com.example.bilibeadsdesigns.R
import com.example.bilibeadsdesigns.bilibeads.models.ProductItem

class DashboardAdapter(
    private val context: Context,
    initialProductList: MutableList<ProductItem>
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    private var productList = mutableListOf<ProductItem>()

    init {
        productList.addAll(initialProductList)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.tv_title)
        val productImage: ImageView = itemView.findViewById(R.id.iv_beads)
        val productPrice: TextView = itemView.findViewById(R.id.tv_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.activity_dashboard_product_display_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]

        // Load image using Glide
        val imagename = product.gallery
        val imagepath =
            "https://bilibeadss-bilibeadss-com.preview-domain.com/products/$imagename"
        Glide.with(context).load(imagepath).into(holder.productImage)

        holder.productName.text = product.title
        holder.productPrice.text = product.price

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ViewProduct::class.java)
            intent.putExtra("product_image", imagepath)
            intent.putExtra("product_title", product.title)
            intent.putExtra("product_price", product.price)

            context.startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setProductList(products: List<ProductItem>) {
        productList.clear()
        productList.addAll(products)
        notifyDataSetChanged()
    }
}

