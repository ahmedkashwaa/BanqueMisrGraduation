package com.example.banqumisrgraduation.presentation.viewmodel

import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.banqumisrgraduation.R
import com.example.banqumisrgraduation.data.model.LoginData
import com.example.banqumisrgraduation.data.model.RegisterBody
import com.example.banqumisrgraduation.data.network.service
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    fun register( userName:String, email:String, password:String) : LiveData<Int?> {
        val register = MutableLiveData<Int?>()
        viewModelScope.launch {
            service.register(RegisterBody(userName, email, password))
                .enqueue(object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        if (response.isSuccessful) {
                            register.value=1
                        } else {
                            register.value = 2
                        }
                    }
                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        register.value = -1
                    }
                })
        }

        return register
    }
}