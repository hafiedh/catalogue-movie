package com.hafidh.cataloguemovie.ui.repository.model.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.hafidh.cataloguemovie.ui.repository.model.local.data.MovieEntity
import com.hafidh.cataloguemovie.ui.repository.model.local.data.TvEntity
import com.hafidh.cataloguemovie.ui.repository.model.local.db.Dao

class LocalRepository constructor(private val dao: Dao){
    fun getMovies(): LiveData<List<MovieEntity>> = dao.getMovieFavorite()

    fun getMovieById(movieId: Int): LiveData<MovieEntity> = dao.getMovieById(movieId)

    fun insertMovies(movies:List<MovieEntity>){
        dao.insertMovie(movies)
    }

    fun setFavoriteMovie(movie: MovieEntity, isFavorited:Boolean){
        movie.isFavorite = isFavorited
        dao.updateMovie(movie)
    }

    fun getMoviePaged(): DataSource.Factory<Int, MovieEntity> {
        return dao.getMoviePaged()
    }

    fun getTv(): LiveData<List<TvEntity>> = dao.getTvFavorite()

    fun getTvShowById(tvShowId: Int): LiveData<TvEntity> = dao.getTvShowById(tvShowId)

    fun insertTvShows(tvShows:List<TvEntity>){
        dao.insertTv(tvShows)
    }

    fun setFavoriteTvShow(tv: TvEntity, isFavorited:Boolean){
        tv.isFavorite = isFavorited
        dao.updateTv(tv)
    }

    fun getTvShowPaged(): DataSource.Factory<Int, TvEntity> {
        return dao.getTvShowPaged()
    }

    companion object {

        private var INSTANCE: LocalRepository? = null

        fun getInstance(dao: Dao): LocalRepository {
            if (INSTANCE == null) {
                INSTANCE =
                    LocalRepository(dao)
            }
            return INSTANCE as LocalRepository
        }
    }
}