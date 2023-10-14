package com.example.banqumisrgraduation.data.network

import com.example.banquemisr.interceptor.HeaderInterceptor
import com.example.banqumisrgraduation.data.model.LoginBody
import com.example.banqumisrgraduation.data.model.LoginData
import com.example.banqumisrgraduation.data.model.Products
import com.example.banqumisrgraduation.data.model.RegisterBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CoffeApi{
   /*
    @GET("product/get")
    fun getProduct() : Call<List<Products>>
  */
    @GET("hot")
    fun getProduct() : Call<List<Products>>

    @POST("register")
    fun register(@Body body: RegisterBody) : Call<Unit>

    @POST("login")
    fun login(@Body body:LoginBody) : Call<LoginData>
}

val httpLogging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

val client = OkHttpClient().newBuilder()
    .addInterceptor(httpLogging)
    .addInterceptor(HeaderInterceptor())
    .build()

val retrofit = Retrofit.Builder()
    //.baseUrl("https://coffee-shop2022.herokuapp.com/")
    .baseUrl("https://api.sampleapis.com/coffee/")
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val service = retrofit.create(CoffeApi::class.java)