package com.hafidh.cataloguemovie.ui.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hafidh.cataloguemovie.databinding.FragmentTvFavoriteBinding
import com.hafidh.cataloguemovie.ui.viewmodel.TvshowViewModel
import com.hafidh.cataloguemovie.ui.viewmodel.ViewModelFactory
import com.hafidh.cataloguemovie.utils.Constans.SPAN_COUNT
import com.hafidh.cataloguemovie.utils.Status

class TvFavoriteFragment : Fragment() {
    private var _binding : FragmentTvFavoriteBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel : TvshowViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvFavoriteBinding.inflate(LayoutInflater.from(inflater.context),container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this,factory)[TvshowViewModel::class.java]

        val adapterTv = context?.let { AdapterTv(it) }

        binding?.rvTvFav?.apply {
            layoutManager = GridLayoutManager(context,SPAN_COUNT)
            adapter = adapterTv
        }

        viewModel.tvPaged().observe(viewLifecycleOwner,{
            if (it != null){
                when(it.status){
                    Status.SUCCESS->{
                        binding?.pbTvFav?.visibility = View.GONE
                        adapterTv?.submitList(it.data)
                        adapterTv?.notifyDataSetChanged()
                    }
                    Status.LOADING-> binding?.pbTvFav?.visibility = View.VISIBLE
                    Status.ERROR-> {
                        binding?.pbTvFav?.visibility = View.GONE
                        Snackbar.make(requireView(),"Something Wrong",Snackbar.LENGTH_SHORT).show()
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