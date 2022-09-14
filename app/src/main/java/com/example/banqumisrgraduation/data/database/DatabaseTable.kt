package com.example.banquemisr.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "orders")
data class DatabaseTable(
    @PrimaryKey (autoGenerate = true)
    val order:Int=0,
    val userid: Int?,
    val id:Int?,
    val name: String?,
    val price: Float?,
    val imgString: String?,
    val size             : Int?    ,
    val sugar            : Int?    ,
    val isAddedToCart    : Boolean?,
    val boughtItemsCount : Int?
)
