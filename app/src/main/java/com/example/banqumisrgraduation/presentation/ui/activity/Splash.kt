package com.example.banqumisrgraduation.presentation.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.banqumisrgraduation.R


@Suppress("DEPRECATION")
class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        Handler().postDelayed({
            // no working API for AUTH now , skip
           // val intent = Intent(this, AuthActivity::class.java)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIMER.toLong())
    }

    companion object {
        private const val SPLASH_TIMER = 900
    }
}