package com.hafidh.cataloguemovie.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.hafidh.cataloguemovie.R
import com.hafidh.cataloguemovie.databinding.ActivityDetailBinding
import com.hafidh.cataloguemovie.ui.Repository.model.remote.ItemData
import com.hafidh.cataloguemovie.ui.Repository.model.remote.ItemDatatv
import com.hafidh.cataloguemovie.utils.Constans.BASE_IMAGE
import com.hafidh.cataloguemovie.utils.Constans.EXTRA_ID_MOVIE
import com.hafidh.cataloguemovie.utils.Constans.EXTRA_ID_TV
import com.hafidh.cataloguemovie.ui.viewmodel.MovieViewModel
import com.hafidh.cataloguemovie.ui.viewmodel.TvshowViewModel
import com.hafidh.cataloguemovie.ui.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getIntExtra(EXTRA_ID_MOVIE, 0)
        val tvId = intent.getIntExtra(EXTRA_ID_TV, 0)


        val viewModelFactoryMovie = ViewModelFactory.getInstance(this)
        val viewModelFactoryTv = ViewModelFactory.getInstance(this)

        val movieViewModel =
            ViewModelProvider(this, viewModelFactoryMovie)[MovieViewModel::class.java]
        val tvViewModel =
            ViewModelProvider(this, viewModelFactoryTv)[TvshowViewModel::class.java]

        movieViewModel.getDetail(movieId).observe(this,
            { movieData ->
                setView(binding)
                setInitMovie(movieData, binding)
            })

        tvViewModel.getDetail(tvId).observe(this,
            { tvData ->
                setView(binding)
                setInitTvshow(tvData, binding)
            }
        )

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
                }
            } else {
                collapseToolbar.setExpandedTitleColor(Color.BLACK)
                bind.toolbar.setBackgroundColor(Color.TRANSPARENT)
                bind.toolbar.visibility = View.GONE
            }
        })
        bind.toolbar.setNavigationIcon(R.drawable.ic_black_24)
        bind.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setInitMovie(data: ItemData, bind: ActivityDetailBinding) {
        setGlideForImage("${BASE_IMAGE}${data.bigPoster}", bind.imgBig)
        setGlideForImage("${BASE_IMAGE}${data.poster}", bind.imgSmall)
        bind.title.text = data.title
        bind.release.text = data.released
        bind.rating.rating = (data.rating?.div(2))?.toFloat() ?: 2.5f
        bind.overview.text = data.overview
    }

    private fun setInitTvshow(data: ItemDatatv, bind: ActivityDetailBinding) {
        setGlideForImage("${BASE_IMAGE}${data.bigPoster}", bind.imgBig)
        setGlideForImage("${BASE_IMAGE}${data.poster}", bind.imgSmall)
        bind.title.text = data.name
        bind.release.text = data.released
        bind.rating.rating = (data.rating?.div(2))?.toFloat() ?: 2.5f
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
        Snackbar.make(bind.root,"Load Success",Snackbar.LENGTH_SHORT).show()
    }

}
