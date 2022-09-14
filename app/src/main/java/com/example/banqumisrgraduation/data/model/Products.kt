package com.example.banqumisrgraduation.data.model

import com.google.gson.annotations.SerializedName

data class Products (

    @SerializedName("productId"          ) var id               : Int?     = null,
    @SerializedName("name"               ) var name             : String?  = null,
    @SerializedName("small_price"        ) var price            : Float?   = null,
    @SerializedName("image"              ) var imgString        : String?  = null,
    @SerializedName("size"               ) var size             : Int?     = 1,
    @SerializedName("sugar"              ) var sugar            : Int?     = 0,
    @SerializedName("is_added_to_cart"   ) var isAddedToCart    : Boolean? = false,
    @SerializedName("bought_items_count" ) var boughtItemsCount : Int?     = 1

)