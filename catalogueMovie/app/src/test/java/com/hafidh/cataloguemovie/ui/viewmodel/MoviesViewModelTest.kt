package com.hafidh.cataloguemovie.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hafidh.cataloguemovie.ui.Repository.model.DataDummy
import com.hafidh.cataloguemovie.ui.Repository.model.MainRepository
import com.hafidh.cataloguemovie.ui.Repository.model.remote.ItemData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mainRepository: MainRepository

    @Mock
    private lateinit var observer: Observer<List<ItemData>>

    @Mock
    private lateinit var observerDetail: Observer<ItemData>

    @Before
    fun setUp() {
       viewModel = MovieViewModel(mainRepository)
    }

    @Test
    fun getMovies() {
        val dataDummy = DataDummy.getDataDummyMovieNetwork()
        val movie = MutableLiveData<List<ItemData>>()
        movie.value = dataDummy
        `when`(mainRepository.movie()).thenReturn(movie)
        val entities = viewModel.getMovie().value
        assertNotNull(entities)
        assertEquals(20, entities?.size)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dataDummy)
    }

    @Test
    fun detail() {
        val dummy = DataDummy.getDataDummyMovieNetwork()[0]
        val movieDetail = MutableLiveData<ItemData>()
        movieDetail.value = DataDummy.getDataDummyMovieNetwork()[0]
        `when`(mainRepository.movieDetail(movieDetail.value!!.id)).thenReturn(movieDetail)
        val detail = viewModel.getDetail(movieDetail.value!!.id)
        assertNotNull(detail)
        assertEquals(movieDetail.value!!.id, viewModel.getDetail(movieDetail.value!!.id).value?.id)
        assertEquals(
            movieDetail.value!!.title,
            viewModel.getDetail(movieDetail.value!!.id).value?.title
        )
        assertEquals(
            movieDetail.value!!.overview,
            viewModel.getDetail(movieDetail.value!!.id).value?.overview
        )
        assertEquals(
            movieDetail.value!!.poster,
            viewModel.getDetail(movieDetail.value!!.id).value?.poster
        )
        assertEquals(
            movieDetail.value!!.released,
            viewModel.getDetail(movieDetail.value!!.id).value?.released
        )
        assertEquals(
            movieDetail.value!!.rating,
            viewModel.getDetail(movieDetail.value!!.id).value?.rating
        )
        viewModel.getDetail(movieDetail.value!!.id).observeForever(observerDetail)
        verify(observerDetail).onChanged(dummy)
    }
}