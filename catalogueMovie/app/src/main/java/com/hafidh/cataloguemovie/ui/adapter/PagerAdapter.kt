package com.hafidh.cataloguemovie.ui.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hafidh.cataloguemovie.R
import com.hafidh.cataloguemovie.ui.fragment.MovieFragment
import com.hafidh.cataloguemovie.ui.fragment.TvshowFragment

class PagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object {
        @StringRes
        private val TITLES = intArrayOf(R.string.movies, R.string.tvshow)
    }

    override fun getCount(): Int = 2
    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MovieFragment()
            1 -> TvshowFragment()
            else -> Fragment()
        }
    override fun getPageTitle(position: Int): CharSequence = context.resources.getString(TITLES[position])
}