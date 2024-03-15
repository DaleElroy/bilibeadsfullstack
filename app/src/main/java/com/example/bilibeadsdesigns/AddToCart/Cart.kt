//package com.example.bilibeadsdesigns.AddToCart
//
//import android.annotation.SuppressLint
//import android.content.ContentValues
//import android.content.Context
//import android.database.Cursor
//import android.database.SQLException
//import android.database.sqlite.SQLiteDatabase
//
//data class DataClassNew(var title: String, var image: Int, var price: String)
//
//class Cart(context: Context) {
//    private val db: SQLiteDatabase = DatabaseHelper(context).writableDatabase
//
//    fun addItemToCart(item: DataClassNew) {
//        val values = ContentValues().apply {
//            put("title", item.title)
//            put("image", item.image)
//            put("price",item.price)
//        }
//
//        db.insert("cart", null, values)
//    }
//
//    @SuppressLint("Range")
//    fun getCartItems(): MutableList<DataClassNew> {
//        val items = mutableListOf<DataClassNew>()
//        val query = "SELECT * FROM cart"
//
//        val cursor: Cursor = db.rawQuery(query, null)
//
//        try {
//            while (cursor.moveToNext()) {
//                val title = cursor.getString(cursor.getColumnIndex("title"))
//                val image = cursor.getInt(cursor.getColumnIndex("image"))
//                val price = cursor.getString(cursor.getColumnIndex("price"))
//                items.add(DataClassNew(title, image, price ))
//            }
//        } catch (e: SQLException) {
//            e.printStackTrace()
//        } finally {
//            cursor.close()
//        }
//        return items
//    }
//    fun deleteCartItem(item: DataClassNew) {
//        db.execSQL("DELETE FROM cart")
//    }
//
//
//
//}

package com.example.bilibeadsdesigns.AddToCart

import com.example.bilibeadsdesigns.bilibeads.models.ProductCart

object Cart {
    private val cartItems: MutableList<ProductCart> = mutableListOf()

    fun addToCart(item: ProductCart) {
        cartItems.add(item)
    }

    fun getCartItems(): List<ProductCart> {
        return cartItems
    }

    fun deleteCartItem(item: ProductCart) {
        cartItems.remove(item)
    }
}

