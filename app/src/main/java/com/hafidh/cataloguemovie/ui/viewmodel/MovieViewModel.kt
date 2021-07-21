package com.hafidh.cataloguemovie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.hafidh.cataloguemovie.ui.repository.model.MainRepository
import com.hafidh.cataloguemovie.ui.repository.model.local.data.MovieEntity
import com.hafidh.cataloguemovie.utils.Resource

class MovieViewModel(private val repository: MainRepository) : ViewModel() {
    fun getMovie(): LiveData<List<MovieEntity>> = repository.movie()
    fun getDetail(id: Int): LiveData<MovieEntity> = repository.movieDetail(id)

    fun insert(data: List<MovieEntity>) {
        repository.insertMovies(data)
    }

    val getMovies: LiveData<Resource<List<MovieEntity>>> = repository.getMovieFromDb()

    fun moviePaged() : LiveData<Resource<PagedList<MovieEntity>>> = repository.getMovieAsPaged()

    fun setFav() {
        getMovieSwitch.value?.data?.let {
            val state = !it.isFavorite
            repository.setFavMovie(it,state)
        }
    }

    val id = MutableLiveData<Int>()

    fun setId(movieId: Int) {
        this.id.value = movieId
    }

    val getMovieSwitch: LiveData<Resource<MovieEntity>> = Transformations.switchMap(id) {
        repository.getMovie(it)
    }

}
