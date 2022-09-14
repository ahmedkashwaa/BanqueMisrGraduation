package com.example.banqumisrgraduation.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.banqumisrgraduation.R
import com.example.banqumisrgraduation.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        CoffeBuyActivity.cartItems.clear()


    }
}