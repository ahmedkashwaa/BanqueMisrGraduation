package com.example.banqumisrgraduation.data.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

import com.google.gson.annotations.SerializedName
@Parcelize
data class Products (

    @SerializedName("id"          ) var id               : Int?     = null,
    @SerializedName("title"               ) var name             : String?  = null,
    @SerializedName("small_price"        ) var price            : Float?   = 10f,
    @SerializedName("image"              ) var imgString        : String?  = null,
    @SerializedName("size"               ) var size             : Int?     = 1,
    @SerializedName("sugar"              ) var sugar            : Int?     = 0,
    @SerializedName("is_added_to_cart"   ) var isAddedToCart    : Boolean? = false,
    @SerializedName("bought_items_count" ) var boughtItemsCount : Int?     = 1

) : Parcelable