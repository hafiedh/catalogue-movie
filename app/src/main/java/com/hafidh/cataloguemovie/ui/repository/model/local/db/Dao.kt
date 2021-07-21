package com.hafidh.cataloguemovie.ui.repository.model.local.db

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.room.Dao
import com.hafidh.cataloguemovie.ui.repository.model.local.data.MovieEntity
import com.hafidh.cataloguemovie.ui.repository.model.local.data.TvEntity

@Dao
interface Dao {
    @Transaction
    @Query("SELECT * FROM movie_table WHERE id = :id")
    fun getMovieById(id: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(movie: List<MovieEntity>): LongArray

    @Update
    fun updateMovie(movie: MovieEntity): Int

    @WorkerThread
    @Query("SELECT * FROM movie_table where isFavorite = 1")
    fun getMovieFavorite(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie_table where isFavorite = 1")
    fun getMoviePaged(): DataSource.Factory<Int, MovieEntity>

    @Transaction
    @Query("SELECT * FROM tv_table WHERE id = :id")
    fun getTvShowById(id: Int): LiveData<TvEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTv(tvShow: List<TvEntity>): LongArray

    @Update
    fun updateTv(tvShow: TvEntity): Int

    @WorkerThread
    @Query("SELECT * FROM tv_table where isFavorite = 1")
    fun getTvFavorite(): LiveData<List<TvEntity>>

    @Query("SELECT * FROM tv_table where isFavorite = 1")
    fun getTvShowPaged(): DataSource.Factory<Int, TvEntity>
}