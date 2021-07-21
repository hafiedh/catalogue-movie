package com.hafidh.cataloguemovie.ui.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hafidh.cataloguemovie.databinding.FragmentMovieFavoriteBinding
import com.hafidh.cataloguemovie.ui.viewmodel.MovieViewModel
import com.hafidh.cataloguemovie.ui.viewmodel.ViewModelFactory
import com.hafidh.cataloguemovie.utils.Constans
import com.hafidh.cataloguemovie.utils.Status


class MovieFavoriteFragment : Fragment() {
    private var _binding : FragmentMovieFavoriteBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel : MovieViewModel
    private lateinit var adapterMovie : AdapterMovie
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieFavoriteBinding.inflate(LayoutInflater.from(inflater.context),container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this,factory)[MovieViewModel::class.java]

        adapterMovie = context?.let { AdapterMovie(it) }!!
        binding?.rvMovieFav?.apply {
            layoutManager = GridLayoutManager(context, Constans.SPAN_COUNT)
            adapter = adapterMovie
        }

        viewModel.moviePaged().observe(viewLifecycleOwner,{
            if(it != null){
                when(it.status){
                    Status.LOADING-> binding?.pbMovieFav?.visibility = View.VISIBLE
                    Status.SUCCESS->{
                        binding?.pbMovieFav?.visibility = View.GONE
                        adapterMovie.submitList(it.data)
                    }
                    Status.ERROR-> {
                        binding?.pbMovieFav?.visibility = View.GONE
                        Snackbar.make(requireView(),"Something Wrong", Snackbar.LENGTH_SHORT).show()
                    }
                }

            }
        })

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}