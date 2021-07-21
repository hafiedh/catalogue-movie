package com.hafidh.cataloguemovie.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hafidh.cataloguemovie.R
import com.hafidh.cataloguemovie.databinding.ActivityMainBinding
import com.hafidh.cataloguemovie.ui.fragment.FavoriteFragment
import com.hafidh.cataloguemovie.ui.fragment.MovieFragment
import com.hafidh.cataloguemovie.ui.fragment.TvshowFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        populateFragment(MovieFragment())
        activityMainBinding.botNav.setOnNavigationItemSelectedListener(onNavigationListener)

    }
    private val onNavigationListener = BottomNavigationView.OnNavigationItemSelectedListener { fr ->
        when (fr.itemId) {
            R.id.nav_movie ->populateFragment(MovieFragment())
            R.id.nav_tv-> populateFragment(TvshowFragment())
            R.id.nav_fav -> populateFragment(FavoriteFragment())
        }
        true
    }
    private fun populateFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fm_container, fragment)
                .commit()
            return true
        }
        return false
    }
}