package com.example.banqumisrgraduation.presentation.viewmodel

import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banqumisrgraduation.data.model.LoginBody
import com.example.banqumisrgraduation.data.model.LoginData
import com.example.banqumisrgraduation.data.network.service
import com.example.banqumisrgraduation.presentation.ui.fragments.LoginFragment
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {



    fun login(email :String, password:String) : LiveData<LoginData?>{
       val login = MutableLiveData<LoginData?>()
        viewModelScope.launch {
            service.login(LoginBody(email,password)).enqueue(object : Callback<LoginData> {
                override fun onResponse(call: Call<LoginData>, response: Response<LoginData>) {
                    if (response.isSuccessful){
                        login.value=response.body()
                        LoginFragment.token = response.body()?.access_token

                    }
                }
                override fun onFailure(call: Call<LoginData>, t: Throwable) {
                    login.value = LoginData(-1,"","","")
                }
            })
        }

        return login
    }

}
