package com.hafidh.cataloguemovie.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.hafidh.cataloguemovie.R
import com.hafidh.cataloguemovie.databinding.FragmentFavoriteBinding
import com.hafidh.cataloguemovie.ui.activity.MainActivity
import com.hafidh.cataloguemovie.ui.adapter.PagerAdapter
import com.hafidh.cataloguemovie.ui.fragment.favorite.MovieFavoriteFragment
import com.hafidh.cataloguemovie.ui.fragment.favorite.TvFavoriteFragment

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(LayoutInflater.from(inflater.context),container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            (activity as MainActivity).supportActionBar?.title = "Favorite"
            val fr = listOf(MovieFavoriteFragment(),TvFavoriteFragment())
            val title = listOf(resources.getString(R.string.movies),resources.getString(R.string.tvshow))
            binding.vp.adapter = PagerAdapter(fr,requireActivity().supportFragmentManager,lifecycle)
            TabLayoutMediator(binding.tab,binding.vp){tab,position->
                tab.text = title[position]
            }.attach()
        }
    }
}