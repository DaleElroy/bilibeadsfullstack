//package com.example.bilibeadsdesigns.AddToCart
//
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.ImageView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.recyclerview.widget.RecyclerView
//import com.example.bilibeadsdesigns.Dashboard.DataClassDashboard
//import com.example.bilibeadsdesigns.R
//import com.example.bilibeadsdesigns.bilibeads.models.ProductCart
//
//class ItemAdapter(private var items: MutableList<ProductCart>, private val cart: Cart) :
//    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val itemImage: ImageView = itemView.findViewById(R.id.iv_beads)
//        val itemDescription: TextView = itemView.findViewById(R.id.tv_title)
//        val itemPrice: TextView = itemView.findViewById(R.id.tv_price)
//        val addToCartButton: Button = itemView.findViewById(R.id.addtocartButton)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_dashboard_product_display_layout, parent, false)
//        return ViewHolder(view)
//    }
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = items[position]
//        holder.itemImage.setImageResource(item.image)
//        holder.itemDescription.text = item.title
//        holder.itemPrice.text = item.price.toString()
//
//        holder.addToCartButton.setOnClickListener {
//            cart.addItemToCart(item)
//            notifyDataSetChanged()
//            val itemTitle = item.title
//            val message = "$itemTitle added to cart"
//            Toast.makeText(holder.itemView.context, message, Toast.LENGTH_SHORT).show()
//            Log.d("ButtonAdd", "Test")
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//}