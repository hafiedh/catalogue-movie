package com.hafidh.cataloguemovie.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.hafidh.cataloguemovie.R
import com.hafidh.cataloguemovie.databinding.ActivityDetailBinding
import com.hafidh.cataloguemovie.ui.repository.model.local.data.MovieEntity
import com.hafidh.cataloguemovie.ui.repository.model.local.data.TvEntity
import com.hafidh.cataloguemovie.ui.viewmodel.MovieViewModel
import com.hafidh.cataloguemovie.ui.viewmodel.TvshowViewModel
import com.hafidh.cataloguemovie.ui.viewmodel.ViewModelFactory
import com.hafidh.cataloguemovie.utils.Constans.BASE_IMAGE
import com.hafidh.cataloguemovie.utils.Constans.EXTRA_ID_MOVIE
import com.hafidh.cataloguemovie.utils.Constans.EXTRA_ID_TV
import com.hafidh.cataloguemovie.utils.Status

class DetailActivity : AppCompatActivity() {
    private var favoriteMenu: Menu? = null
    private lateinit var binding: ActivityDetailBinding
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var tvViewModel: TvshowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val movieId = intent.getIntExtra(EXTRA_ID_MOVIE, 0)
        val tvId = intent.getIntExtra(EXTRA_ID_TV, 0)


        val viewModelFactoryMovie = ViewModelFactory.getInstance(this)
        val viewModelFactoryTv = ViewModelFactory.getInstance(this)

        movieViewModel =
            ViewModelProvider(this, viewModelFactoryMovie)[MovieViewModel::class.java]
        tvViewModel =
            ViewModelProvider(this, viewModelFactoryTv)[TvshowViewModel::class.java]

        if (movieId != 0) {
            movieViewModel.getDetail(movieId).observe(this) {
                binding.pbDetail.visibility = View.GONE
                setView(binding)
                setInitMovie(it, binding)
                movieViewModel.setId(movieId)
            }
        } else {
            tvViewModel.getDetail(tvId).observe(this) {
                binding.pbDetail.visibility = View.GONE
                setView(binding)
                setInitTvShow(it, binding)
                tvViewModel.setId(tvId)
            }
        }
    }
    private fun collapseToolbar(bind: ActivityDetailBinding) {
        val collapseToolbar = bind.collapseToolbar
        bind.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            if (collapseToolbar.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(
                    collapseToolbar
                )
            ) {
                collapseToolbar.setCollapsedTitleTextColor(Color.BLACK)
                bind.toolbar.also {
                    it.setBackgroundColor(Color.WHITE)
                    it.visibility = View.VISIBLE
                    it.setNavigationIcon(R.drawable.ic_back)
                }
            } else {
                collapseToolbar.setExpandedTitleColor(Color.BLACK)
                bind.toolbar.setBackgroundColor(Color.TRANSPARENT)
                bind.toolbar.visibility = View.VISIBLE
                bind.toolbar.setNavigationIcon(R.drawable.ic_back)
            }

        })
        bind.toolbar.setNavigationIcon(R.drawable.ic_back)
        bind.toolbar.setNavigationOnClickListener { onBackPressed() }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite, menu)
        this.favoriteMenu = menu
        if (intent.getIntExtra(EXTRA_ID_MOVIE, 0) != 0) {
            movieViewModel.getMovieSwitch.observe(this) { movie ->
                movie?.let {
                    when (movie.status) {
                        Status.LOADING -> binding.pbDetail.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.pbDetail.visibility = View.GONE
                            movie.data?.let {
                                val state = it.isFavorite
                                setFavorite(state)
                            }
                        }
                        Status.ERROR -> {
                            binding.pbDetail.visibility = View.GONE
                            Snackbar.make(
                                binding.root,
                                "Something Wrong, please check you connection",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        } else {
            tvViewModel.getTvSwitch.observe(this) { tv ->
                tv?.let {
                    when (tv.status) {
                        Status.LOADING -> binding.pbDetail.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            tv.data?.let {
                                binding.pbDetail.visibility = View.GONE
                                val state = it.isFavorite
                                setFavorite(state)
                            }
                        }
                        Status.ERROR -> {
                            binding.pbDetail.visibility = View.GONE
                            Snackbar.make(
                                binding.root,
                                "Something Wrong, please check you connection",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.favorite) {
            if (intent.getIntExtra(EXTRA_ID_MOVIE, 0) != 0) {
                movieViewModel.setFav()
            } else {
                tvViewModel.setFav()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setInitMovie(data: MovieEntity, bind: ActivityDetailBinding) {
        setGlideForImage("${BASE_IMAGE}${data.bigPoster}", bind.imgBig)
        setGlideForImage("${BASE_IMAGE}${data.poster}", bind.imgSmall)
        bind.title.text = data.title
        bind.release.text = data.released
        bind.rating.rating = ((data.rating?.div(2))?.toFloat()) ?: 0.toFloat()
        bind.overview.text = data.overview
    }

    private fun setInitTvShow(data: TvEntity, bind: ActivityDetailBinding) {
        setGlideForImage("${BASE_IMAGE}${data.bigPoster}", bind.imgBig)
        setGlideForImage("${BASE_IMAGE}${data.poster}", bind.imgSmall)
        bind.title.text = data.name
        bind.release.text = data.released
        bind.rating.rating = (data.rating?.div(2))?.toFloat() ?: 0.toFloat()
        bind.overview.text = data.overview
    }

    private fun setGlideForImage(url: String, img: ImageView) {
        Glide.with(this)
            .load(url)
            .into(img)
    }

    private fun setView(bind: ActivityDetailBinding) {
        bind.pbDetail.visibility = View.GONE
        collapseToolbar(bind)
        Snackbar.make(bind.root, "Load Success", Snackbar.LENGTH_SHORT).show()
    }

    private fun setFavorite(state: Boolean) {
        if (favoriteMenu == null) return
        val menu = favoriteMenu?.findItem(R.id.favorite)
        if (state) {
            menu?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_red)
        } else {
            menu?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        }
    }

}
