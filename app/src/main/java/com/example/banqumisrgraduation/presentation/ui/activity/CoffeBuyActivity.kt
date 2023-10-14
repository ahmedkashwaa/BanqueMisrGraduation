package com.example.banqumisrgraduation.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.banqumisrgraduation.R
import com.example.banqumisrgraduation.data.model.Products
import com.example.banqumisrgraduation.databinding.ActivityCoffeBuyBinding
import com.example.banqumisrgraduation.presentation.viewmodel.CartViewModel

class CoffeBuyActivity : AppCompatActivity() {
companion object {
    var cartItems = mutableListOf<Products>()
}
    var coffeePrice : Double? = 0.0
    var count =1
    var coffePhoto : String? = null
    var coffeName : String? = null
    var coffeeSize = 1
    var CoffeeSugar = 0
    var p: Double = 0.0
    private lateinit var binding: ActivityCoffeBuyBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoffeBuyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getIntExtra("coffeID", 0)

        if (cartItems.any { it.id ==id }) {
            cartItems.find { it.id == id }
                .apply {
                count =   this?.boughtItemsCount!!
                coffeeSize=   this.size!!
                CoffeeSugar=   this.sugar!!
                when(coffeeSize){
            1-> coffeePrice= this.price!!.toDouble()/count
            2-> coffeePrice= this.price!!.toDouble()/count/1.5
            3-> coffeePrice= this.price!!.toDouble()/count/2
                }
                coffePhoto = this.imgString
                coffeName = this.name
            }
        } else {

             coffeePrice = intent.getDoubleExtra("Price", 0.00)
             count = intent.getIntExtra("Count",1)
             coffePhoto = intent.getStringExtra("coffePhoto")
             coffeName = intent.getStringExtra("coffeName")
             coffeeSize = intent.getIntExtra("Size",1)
             CoffeeSugar = intent.getIntExtra("Sugar",0)// get it from products object
        }
         p = coffeePrice!!

      size_price()

        when (CoffeeSugar) {
            0 -> binding.noSugar.isChecked = true
            1-> binding.oneSugar.isChecked = true
            2-> binding.twoSugar.isChecked = true
            3-> binding.threeSugar.isChecked = true
        }

        binding.quantity.text = count.toString()
        binding.coffeName.text = coffeName
        binding.namePrice.text = coffeName
        binding.price.text = "${p!!*count} EGP"


       Glide.with(this)
            .load(coffePhoto)
            .into(binding.coffePhoto)
        binding.coffePhoto.setOnClickListener {
           it.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake_animation))

        }

        binding.addToCart.setOnClickListener {
              if (cartItems.any { it.id ==id }) {
                  cartItems.find {
                      it.id == id
                  }.apply {
                      this?.boughtItemsCount = count
                      this?.price = (count * p).toFloat()
                      this?.size = coffeeSize
                      this?.sugar = CoffeeSugar
                  }
              } else {
                  cartItems.add(Products(id,
                      coffeName!!,
                      (count * p).toFloat(),
                      coffePhoto!!,
                      coffeeSize,
                      CoffeeSugar,
                      true,
                      count))
              }
            Toast.makeText(this, "Added To Cart", Toast.LENGTH_SHORT).show()
             onBackPressed()
        }
      sugarRadioGroupClicks()
      sizeRadioGroupClicks()


        binding.plus.setOnClickListener {
            count++
            binding.price.text = "${(count * p)} EGP"
            binding.quantity.text = count.toString()
        }
        binding.minus.setOnClickListener {
            if (count > 1) {
                count--
                binding.price.text = "${(count * p)} EGP"
                binding.quantity.text = count.toString()
            }
        }
    }

    fun onBack(view: View) {
        onBackPressed()
    }
    fun size_price(){
        when (coffeeSize) {
            1 ->{
                p = coffeePrice!!
                binding.small.isChecked = true
                binding.coffePhoto.animate().scaleY(1f)
                    .scaleX(1f)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .setDuration(300)
            }
            2-> {
                p = coffeePrice!! * 1.5
                binding.medium.isChecked = true
                binding.coffePhoto.animate().scaleY(1.15f)
                    .scaleX(1.15f)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .setDuration(300)
            }
            3-> {
                p = coffeePrice!! * 2
                binding.large.isChecked = true
                binding.coffePhoto.animate().scaleY(1.35f)
                    .scaleX(1.35f)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .setDuration(300)
            }
        }
    }

    fun sugarRadioGroupClicks(){
        binding.rgSugar.setOnCheckedChangeListener { radioGroup, i ->
            CoffeeSugar = when (i) {
                R.id.no_sugar -> 0
                R.id.one_sugar -> 1
                R.id.two_sugar -> 2
                R.id.three_sugar -> 3
                else -> {
                    0
                }
            }
        }
    }

    fun sizeRadioGroupClicks(){
        binding.rgSize.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.small -> {
                    p = coffeePrice!!
                    coffeeSize = 1
                    binding.coffePhoto.animate().scaleY(1f)
                        .scaleX(1f)
                        .setInterpolator(AccelerateDecelerateInterpolator())
                        .setDuration(300)

                }
                R.id.medium -> {
                    p = coffeePrice!! * 1.5
                    coffeeSize = 2
                    binding.coffePhoto.animate().scaleY(1.15f)
                        .scaleX(1.15f)
                        .setInterpolator(AccelerateDecelerateInterpolator())
                        .setDuration(300)
                }
                R.id.large -> {
                    p = coffeePrice!! * 2
                    coffeeSize = 3
                    binding.coffePhoto.animate().scaleY(1.35f)
                        .scaleX(1.35f)
                        .setInterpolator(AccelerateDecelerateInterpolator())
                        .setDuration(300)
                }
                else -> {
                    p = coffeePrice!!
                }
            }
            binding.price.text = "${(count * p)} EGP"

        }
    }

}
