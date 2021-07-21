package com.hafidh.cataloguemovie.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafidh.cataloguemovie.databinding.FragmentTvshowBinding
import com.hafidh.cataloguemovie.ui.repository.model.local.data.TvEntity
import com.hafidh.cataloguemovie.ui.activity.DetailActivity
import com.hafidh.cataloguemovie.ui.activity.MainActivity
import com.hafidh.cataloguemovie.ui.adapter.TvShowAdapter
import com.hafidh.cataloguemovie.ui.viewmodel.TvshowViewModel
import com.hafidh.cataloguemovie.ui.viewmodel.ViewModelFactory
import com.hafidh.cataloguemovie.utils.Constans
import com.hafidh.cataloguemovie.utils.Constans.EXTRA_ID_TV
import com.hafidh.cataloguemovie.utils.SetOnClickListener
import com.hafidh.cataloguemovie.utils.Status


class TvshowFragment : Fragment() {
    private lateinit var tvAdapter: TvShowAdapter
    private lateinit var binding: FragmentTvshowBinding
    private lateinit var viewModel: TvshowViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvshowBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = "Tv Show"
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[TvshowViewModel::class.java]

        tvAdapter = TvShowAdapter()
        setUpRecyclerView(binding)
        setOnclickListener()

        viewModel.getTv().observe(viewLifecycleOwner, {
            binding.pbTvshow.visibility = View.GONE
            tvAdapter.addList(it)
            getTvToDb(it)
        })


    }

    private fun setUpRecyclerView(bind: FragmentTvshowBinding) {
        bind.rvTvshow.also { rv ->
            rv.layoutManager = GridLayoutManager(context, Constans.SPAN_COUNT)
            rv.setHasFixedSize(true)
            rv.adapter = tvAdapter
            rv.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun setOnclickListener() {
        tvAdapter.setOnItemClickListener(object : SetOnClickListener {
            override fun onClicked(view: View, position: Int) {
                val id = tvAdapter.getData()[position].id
                Intent(context, DetailActivity::class.java).also {
                    it.putExtra(EXTRA_ID_TV, id)
                    startActivity(it)
                }
            }
        })
    }

    private fun getTvToDb(data: List<TvEntity>) {
        viewModel.getTvShows().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.pbTvshow.visibility = View.GONE
                    if (it.data != null) {
                        viewModel.insert(data)
                    }
                }
                Status.LOADING -> {}
                Status.ERROR -> binding.pbTvshow.visibility = View.GONE
            }
        })
    }
}
