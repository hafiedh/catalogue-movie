package com.hafidh.cataloguemovie.utils

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.hafidh.cataloguemovie.ui.repository.model.local.data.MovieEntity
import com.hafidh.cataloguemovie.ui.repository.model.local.data.TvEntity

interface Source {
    fun movie(): LiveData<List<MovieEntity>>
    fun movieDetail(movieId: Int): LiveData<MovieEntity>
    fun tvShow(): LiveData<List<TvEntity>>
    fun tvShowDetail(tvShowId: Int): LiveData<TvEntity>

    fun getMovieFromDb(): LiveData<Resource<List<MovieEntity>>>
    fun getMovie(movieId: Int): LiveData<Resource<MovieEntity>>?
    fun setFavMovie(movie: MovieEntity, isFavorited: Boolean)
    fun insertMovies(movies: List<MovieEntity>)
    fun getMovieAsPaged(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getTv(): LiveData<Resource<List<TvEntity>>>?
    fun getTvShow(tvShowId: Int): LiveData<Resource<TvEntity>>?
    fun setFavTv(tvShow: TvEntity, isFavorite: Boolean)
    fun insertTv(tvShows: List<TvEntity>)
    fun getTvShowAsPaged(): LiveData<Resource<PagedList<TvEntity>>>
}