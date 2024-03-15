//package com.example.bilibeadsdesigns.AddToCart
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.ImageView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.recyclerview.widget.RecyclerView
//import com.example.bilibeadsdesigns.R
//import com.example.bilibeadsdesigns.bilibeads.models.ProductCart
//
//class CartAdapter(private val cartItems: MutableList<ProductCart>, private val cart: Cart) :
//    RecyclerView.Adapter<CartAdapter.ViewHolder>() {
//
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val itemImage: ImageView = itemView.findViewById(R.id.iv_beads)
//        val itemDescription: TextView = itemView.findViewById(R.id.tv_title)
//        //added
//        val itemDelete: Button = itemView.findViewById(R.id.delete_tv)
//        val itemPrice: TextView = itemView.findViewById(R.id.tv_price)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_cart_product_display_layout, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val dataClassDashboard = cartItems[position]
////        holder.itemImage.setImageResource(dataClassDashboard.image)
//        holder.itemDescription.text = dataClassDashboard.title
//        holder.itemPrice.text = dataClassDashboard.price.toString()
//
//
//        holder.itemDelete.setOnClickListener {
//            val itemToDelete = cartItems[position]
//            cart.deleteCartItem(itemToDelete)
//            cartItems.removeAt(position)
//            notifyDataSetChanged()
//            val itemTitle = itemToDelete.title
//            val message = "$itemTitle Removed from the cart"
//            Toast.makeText(holder.itemView.context, message, Toast.LENGTH_SHORT).show()
//        }
//
//    }
//    override fun getItemCount(): Int {
//        return cartItems.size
//    }
//
//
//}
//

// CartAdapter.kt
package com.example.bilibeadsdesigns.AddToCart

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bilibeadsdesigns.Dashboard.ViewProduct
import com.example.bilibeadsdesigns.R
import com.example.bilibeadsdesigns.bilibeads.models.ProductCart

class CartAdapter(private val context: Context, private var cartItems: MutableList<ProductCart>) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.iv_beads)
        val itemTitle: TextView = itemView.findViewById(R.id.tv_title)
        val itemPrice: TextView = itemView.findViewById(R.id.tv_price)
        val itemDelete: Button = itemView.findViewById(R.id.delete_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_cart_product_display_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = cartItems[position]

        // Load image using Glide
//        Glide.with(context).load(product.productPhoto).into(holder.itemImage)

        val imagename = product.product_photo
        val imagepath =
            "https://bilibead-bilibead-com.preview-domain.com/public/storage/addtocart/$imagename"
        Glide.with(context).load(imagepath).into(holder.itemImage)

        holder.itemTitle.text = product.product_title
        holder.itemPrice.text = product.product_price

        holder.itemDelete.setOnClickListener {
            cartItems.removeAt(position)
            notifyItemRemoved(position)
            // You might want to notify the API here to remove the item from the server-side cart
            // and handle success/failure accordingly
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ViewProduct::class.java)
            intent.putExtra("product_image", imagepath)
            intent.putExtra("product_title", product.product_title)
            intent.putExtra("product_price", product.product_price)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<ProductCart>) {
        cartItems.clear()
        cartItems.addAll(items)
        notifyDataSetChanged()
    }
}


