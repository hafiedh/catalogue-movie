package com.hafidh.cataloguemovie.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.hafidh.cataloguemovie.ui.repository.model.MainRepository
import com.hafidh.cataloguemovie.ui.repository.model.local.data.MovieEntity
import com.hafidh.cataloguemovie.ui.utils.DataDummy
import com.hafidh.cataloguemovie.ui.utils.LiveDataTestUnit
import com.hafidh.cataloguemovie.utils.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mainRepository: MainRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Mock
    private lateinit var observerDetail: Observer<MovieEntity>

    @Mock
    private lateinit var observerDb: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var observerMoviePaged: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(mainRepository)
    }

    @Test
    fun getMovies() {
        val dataDummy = DataDummy.getDataDummyMovieNetwork()
        val movie = MutableLiveData<List<MovieEntity>>()
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
        val movieDetail = MutableLiveData<MovieEntity>()
        movieDetail.value = DataDummy.getDataDummyMovieNetwork()[0]
        `when`(movieDetail.value?.id?.let { mainRepository.movieDetail(it) }).thenReturn(movieDetail)
        val detail = movieDetail.value?.id?.let { viewModel.getDetail(it) }
        assertNotNull(detail)
        assertEquals(movieDetail.value?.id,
            movieDetail.value?.id?.let { viewModel.getDetail(it).value?.id })
        assertEquals(
            movieDetail.value?.title,
            movieDetail.value?.id?.let { viewModel.getDetail(it).value?.title }
        )
        assertEquals(
            movieDetail.value?.overview,
            movieDetail.value?.id?.let { viewModel.getDetail(it).value?.overview }
        )
        assertEquals(
            movieDetail.value?.poster,
            movieDetail.value?.id?.let { viewModel.getDetail(it).value?.poster }
        )
        assertEquals(
            movieDetail.value?.released,
            movieDetail.value?.id?.let { viewModel.getDetail(it).value?.released }
        )
        assertEquals(
            movieDetail.value?.rating,

            movieDetail.value?.id?.let { viewModel.getDetail(it).value?.rating }
        )
        movieDetail.value?.id?.let { viewModel.getDetail(it).observeForever(observerDetail) }
        verify(observerDetail).onChanged(dummy)
    }

    @Test
    fun getDetail() {
        val dummy = Resource.success(DataDummy.getDataDummyMovieDetailNetwork())
        val movieDetail = MutableLiveData<Resource<MovieEntity>>()
        movieDetail.value = dummy
        `when`(movieDetail.value?.data?.id?.let { mainRepository.getMovie(it) }).thenReturn(
            movieDetail
        )
        viewModel.getMovieSwitch.observeForever(observerDb)
        val result =
            LiveDataTestUnit.getValue(mainRepository.getMovie(movieDetail.value?.data!!.id))
        assertNotNull(result)
    }

    @Test
    fun insertFavorite() {
        val dummy = DataDummy.getDataDummyMovieNetwork()
        viewModel.insert(dummy)
        verify(mainRepository).insertMovies(dummy)
        assertNotNull(dummy)
    }

    @Test
    fun moviePaged() {
        val dummy = Resource.success(pagedList)
        `when`(dummy.data?.size).thenReturn(4)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummy
        `when`(mainRepository.getMovieAsPaged()).thenReturn(movies)
        val result = viewModel.moviePaged().value?.data
        verify(mainRepository).getMovieAsPaged()
        assertNotNull(result)
        assertEquals(4 , result?.size)
        viewModel.moviePaged().observeForever(observerMoviePaged)
        verify(observerMoviePaged).onChanged(dummy)
    }

}
