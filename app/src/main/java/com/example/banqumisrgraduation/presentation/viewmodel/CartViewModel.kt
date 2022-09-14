package com.example.banqumisrgraduation.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.banqumisrgraduation.data.model.Products
import com.example.banqumisrgraduation.presentation.ui.activity.CoffeBuyActivity

class CartViewModel : ViewModel() {

    private val _products = MutableLiveData<List<Products>>()
    val products: LiveData<List<Products>>
        get() = _products

    private val _totalprice = MutableLiveData<Float>().apply {
        value = 0.00f
    }
    val totalprice: LiveData<Float> = _totalprice

    init {
       getData()
    }

    fun getData() {
            _products.value = CoffeBuyActivity.cartItems
            changePrice()



    }

    fun changePrice(){
        _totalprice.value=0f
        (_products.value as MutableList<Products>).forEach {
            _totalprice.value = _totalprice.value?.plus(it.price!!.toFloat())

        }
    }


}