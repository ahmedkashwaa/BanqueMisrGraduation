package com.example.banqumisrgraduation.presentation.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.banqumisrgraduation.R
import com.example.banqumisrgraduation.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)



    }


    override fun onDestroy() {
        super.onDestroy()
        //CoffeBuyActivity.cartItems.clear()
    }
    @SuppressLint("ResourceAsColor")
    override fun onResume() {
        super.onResume()
        val badge= navView.getOrCreateBadge(R.id.navigation_cart)
        if (CoffeBuyActivity.cartItems.size>0){
            badge.isVisible=true
            badge.number= CoffeBuyActivity.cartItems.size
        } else {
            badge.isVisible=false
        }

    }
    companion object{
         fun refreshBadge(activity: MainActivity){
            val navView: BottomNavigationView = activity.findViewById(R.id.nav_view)
            val badge= navView.getOrCreateBadge(R.id.navigation_cart)
            if (CoffeBuyActivity.cartItems.size>0){
                badge.isVisible=true
                badge.number= CoffeBuyActivity.cartItems.size
            } else {
                badge.isVisible=false
            }
        }
    }




}