package com.example.banqumisrgraduation.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banqumisrgraduation.data.model.Products
import com.example.banqumisrgraduation.data.network.service
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductCatalogViewModel : ViewModel() {

    private val _products = MutableLiveData<List<Products>>()
    val products: LiveData<List<Products>>
        get() = _products

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            service.getProduct().enqueue(object : Callback<List<Products>> {
                override fun onResponse(
                    call: Call<List<Products>>,
                    response: Response<List<Products>>,
                ) {
                    _products.value = response.body()
                }

                override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                    Log.e("error", t.localizedMessage as String)
                }
            })
        }

    }


}