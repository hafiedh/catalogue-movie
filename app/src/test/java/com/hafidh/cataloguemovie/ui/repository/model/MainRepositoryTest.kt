package com.hafidh.cataloguemovie.ui.repository.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.hafidh.cataloguemovie.ui.repository.model.local.LocalRepository
import com.hafidh.cataloguemovie.ui.repository.model.local.data.MovieEntity
import com.hafidh.cataloguemovie.ui.repository.model.local.data.TvEntity
import com.hafidh.cataloguemovie.ui.repository.model.remote.NetworkRepository
import com.hafidh.cataloguemovie.ui.utils.AppExecutor
import com.hafidh.cataloguemovie.ui.utils.DataDummy
import com.hafidh.cataloguemovie.ui.utils.LiveDataTestUnit
import com.hafidh.cataloguemovie.ui.utils.PagedList
import com.hafidh.cataloguemovie.utils.Resource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


class MainRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(NetworkRepository::class.java)
    private val local = mock(LocalRepository::class.java)
    private val appExecutors = mock(AppExecutor::class.java)
    private val mainRepositoryTest = FakeMainRepository(remote, local, appExecutors)
    private val movies = DataDummy.getDataDummyMovieNetwork()
    private val id = movies[0].id
    private val tvShow = DataDummy.getDataDummyTvshowNetwork()
    private val tvShowId = tvShow[0].id
    private val tvShowDetail = DataDummy.getDataDummyTvShowDetailNetwork()


    @Test
    fun movie() {
        Mockito.doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as NetworkRepository.MovieCallback)
                .movieResponseCallback(movies)
            null
        }.`when`(remote).getMovieDb(any())
        val movieData = LiveDataTestUnit.getValue(mainRepositoryTest.movie())
        verify(remote).getMovieDb(any())
        assertNotNull(movieData)
        assertEquals(movies.size, movieData.size)
    }

    @Test
    fun movieDetail() {
        Mockito.doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[1] as NetworkRepository.MovieDetailCallback)
                .movieDetailResponseCallback(movies[1])
            null
        }.`when`(remote).getMovieDetailDb(eq(id), any())
        val movieDataDetail = LiveDataTestUnit.getValue(mainRepositoryTest.movieDetail(id))
        verify(remote).getMovieDetailDb(eq(id), any())
        assertNotNull(movieDataDetail)
        assertEquals(movies[1].title, movieDataDetail.title)
    }

    @Test
    fun tvShow() {
        Mockito.doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as NetworkRepository.TvShowCallback)
                .tvResponseCallback(tvShow)
            null
        }.`when`(remote).getTvShowDb(any())
        val tvData = LiveDataTestUnit.getValue(mainRepositoryTest.tvShow())
        verify(remote).getTvShowDb(any())
        assertNotNull(tvData)
        assertEquals(tvShow.size, tvData.size)
    }

    @Test
    fun tvShowDetail() {
        Mockito.doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[1] as NetworkRepository.TvShowDetailCallback)
                .tvDetailResponseCallback(tvShowDetail)
            null
        }.`when`(remote).getTvShowDetailDb(eq(tvShowId), any())
        val tvDataDetail = LiveDataTestUnit.getValue(mainRepositoryTest.tvShowDetail(tvShowId))
        verify(remote).getTvShowDetailDb(eq(tvShowId), any())
        assertNotNull(tvDataDetail)
        assertEquals(tvShowDetail.name, tvDataDetail.name)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getMoviesDb() {
        val sourceFactory = mock(DataSource.Factory::class.java)
        `when`(local.getMoviePaged())
            .thenReturn(sourceFactory as DataSource.Factory<Int, MovieEntity>)
        mainRepositoryTest.getMovieAsPaged()
        verify(local).getMoviePaged()
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = DataDummy.getMovie(id)
        `when`(local.getMovieById(id)).thenReturn(movie)
        val data = LiveDataTestUnit.getValue(mainRepositoryTest.getMovie(id))
        assertNotNull(data)
        verify(local).getMovieById(id)
    }


    @Test
    @Suppress("UNCHECKED_CAST")
    fun getTvDb() {
        val sourceFactory = mock(DataSource.Factory::class.java)
        `when`(local.getTvShowPaged())
            .thenReturn(sourceFactory as DataSource.Factory<Int, TvEntity>)
        mainRepositoryTest.getTvShowAsPaged()
        verify(local).getTvShowPaged()
    }

    @Test
    fun getTvShow() {
        val tv = MutableLiveData<TvEntity>()
        tv.value = DataDummy.getTv(tvShowId)
        `when`(local.getTvShowById(tvShowId)).thenReturn(tv)
        val data = LiveDataTestUnit.getValue(mainRepositoryTest.getTvShow(tvShowId))
        assertNotNull(data)
        verify(local).getTvShowById(tvShowId)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getMoviePaged() {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMoviePaged()).thenReturn(dataSource)
        mainRepositoryTest.getMovieAsPaged()
        val movieEntities = Resource.success(PagedList.mockPagedList(DataDummy.getDataDummyMovieNetwork()))
        verify(local).getMoviePaged()
        assertNotNull(movieEntities)
        assertEquals(movies.size, movieEntities.data?.size)
    }
}