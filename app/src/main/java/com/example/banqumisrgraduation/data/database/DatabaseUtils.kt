package com.example.banqumisrgraduation.data.database

import com.example.banquemisr.database.DatabaseTable
import com.example.banqumisrgraduation.data.model.Products


fun List<Products>.asEntities(userId:Int) : List<DatabaseTable> {
    return map {
        DatabaseTable(
            userid=userId,
            id = it.id,
            name = it.name,
            price = it.price,
            imgString = it.imgString,
            size = it.size,
            sugar = it.sugar,
            boughtItemsCount = it.boughtItemsCount,
            isAddedToCart = it.isAddedToCart
        )
    }
    
}

fun List<DatabaseTable>.asProducts() : List<Products> {
    return map {
        Products(
            id = it.id,
            name = it.name,
            price = it.price,
            imgString = it.imgString,
            size = it.size,
            sugar = it.sugar,
            boughtItemsCount = it.boughtItemsCount,
            isAddedToCart = it.isAddedToCart
        )
    }

}
