package com.example.bilibeadsdesigns.Dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bilibeadsdesigns.R
import com.example.bilibeadsdesigns.bilibeads.models.ProductItem

class DashboardAdapter(private val getActivity: Dashboard, private val productList: MutableList<ProductItem>) :
    RecyclerView.Adapter<DashboardAdapter.MyViewHolder>() {

    var onItemClick: ((ProductItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_dashboard_product_display_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = productList[position]
        holder.productTitle.text = product.name
        holder.productPrice.text = product.price

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(product)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productTitle: TextView = itemView.findViewById(R.id.tv_title)
        val productImage: ImageView = itemView.findViewById(R.id.iv_beads)
        val productPrice: TextView = itemView.findViewById(R.id.tv_price)
    }
}
