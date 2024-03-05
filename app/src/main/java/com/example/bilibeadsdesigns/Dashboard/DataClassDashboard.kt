package com.example.bilibeadsdesigns.Dashboard

//import android.os.Parcel
//import android.os.Parcelable
//
//
////currency nag add ako
////data class DataClassDashboard(var title: String, var image: Int, var price: Int, var currency: String)
//data class DataClassDashboard(var title: String, var image: Int, var price: String) : Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readString()!!,
//        parcel.readInt(),
//        parcel.readString()!!,
//
//    )
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(title)
//        parcel.writeInt(image)
//        parcel.writeString(price)
//
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<DataClassDashboard> {
//        override fun createFromParcel(parcel: Parcel): DataClassDashboard {
//            return DataClassDashboard(parcel)
//        }
//
//        override fun newArray(size: Int): Array<DataClassDashboard?> {
//            return arrayOfNulls(size)
//        }
//    }
//}

data class DataClassDashboard(var title: String, var image: Int, var price: String)
