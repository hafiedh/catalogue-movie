package com.hafidh.cataloguemovie.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.hafidh.cataloguemovie.R
import com.hafidh.cataloguemovie.databinding.ActivitySplashScreenBinding
import com.hafidh.cataloguemovie.utils.Constans.DELAY

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val top = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        binding.imgSplash.startAnimation(bottom)
        binding.tvSplash.startAnimation(top)

        Handler(mainLooper).postDelayed({
            val move = Intent(this, MainActivity::class.java)
            startActivity(move)
            finish()
        }, DELAY)
    }
}