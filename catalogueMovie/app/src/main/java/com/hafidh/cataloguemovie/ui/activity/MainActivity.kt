package com.hafidh.cataloguemovie.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hafidh.cataloguemovie.databinding.ActivityMainBinding
import com.hafidh.cataloguemovie.ui.adapter.PagerAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val sectionsPagerAdapter = PagerAdapter(this, supportFragmentManager)
        activityMainBinding.viewPager.adapter = sectionsPagerAdapter
        activityMainBinding.tabs.setupWithViewPager(activityMainBinding.viewPager)
        supportActionBar?.elevation = 0f
    }

}