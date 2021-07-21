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
import com.hafidh.cataloguemovie.databinding.FragmentMovieBinding
import com.hafidh.cataloguemovie.ui.repository.model.local.data.MovieEntity
import com.hafidh.cataloguemovie.ui.activity.DetailActivity
import com.hafidh.cataloguemovie.ui.activity.MainActivity
import com.hafidh.cataloguemovie.ui.adapter.MovieAdapter
import com.hafidh.cataloguemovie.utils.Constans.EXTRA_ID_MOVIE
import com.hafidh.cataloguemovie.utils.Constans.SPAN_COUNT
import com.hafidh.cataloguemovie.utils.SetOnClickListener
import com.hafidh.cataloguemovie.ui.viewmodel.MovieViewModel
import com.hafidh.cataloguemovie.ui.viewmodel.ViewModelFactory
import com.hafidh.cataloguemovie.utils.Status


class MovieFragment : Fragment() {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel
    private var movieList : List<MovieEntity> = listOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = "Movie"
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        movieAdapter = MovieAdapter()
        setUpRecyclerView(binding)
        setOnclickListener()

        viewModel.getMovie().observe(viewLifecycleOwner, {
            binding.pbMovie.visibility = View.GONE
            movieList = it
            movieAdapter.addList(it)
            getMovieToDb()
        })
    }

    private fun setUpRecyclerView(bind: FragmentMovieBinding) {
        bind.rvMovie.also { rv ->
            rv.layoutManager = GridLayoutManager(context, SPAN_COUNT)
            rv.setHasFixedSize(true)
            rv.adapter = movieAdapter
            rv.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun setOnclickListener() {
        movieAdapter.setOnItemClickListener(object : SetOnClickListener {
            override fun onClicked(view: View, position: Int) {
                val id = movieAdapter.getData()[position].id
                Intent(context, DetailActivity::class.java).also {
                    it.putExtra(EXTRA_ID_MOVIE, id)
                    startActivity(it)
                }
            }
        })
    }

    private fun getMovieToDb() {
        viewModel.getMovies.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.pbMovie.visibility = View.GONE
                    if (it.data != null) {
                        viewModel.insert(movieList)
                    }
                }
                Status.LOADING -> {
                }
                Status.ERROR -> binding.pbMovie.visibility = View.GONE
            }
        })
    }
}
