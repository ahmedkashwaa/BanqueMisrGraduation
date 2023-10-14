package com.example.banquemisr.interceptor

import android.annotation.SuppressLint
import com.example.banqumisrgraduation.presentation.ui.fragments.LoginFragment
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    @SuppressLint("SuspiciousIndentation")
    override fun intercept(chain: Interceptor.Chain): Response {

     val builder =   chain.request().newBuilder()
         .addHeader("Authorization", "Bearer " + LoginFragment.token)
         .build()

        return chain.proceed(builder)
    }
}