package com.hafidh.cataloguemovie.ui.Repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hafidh.cataloguemovie.ui.Repository.model.DataDummy
import com.hafidh.cataloguemovie.ui.Repository.model.LiveDataTestUnit
import com.hafidh.cataloguemovie.ui.Repository.model.remote.NetworkRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.doAnswer


class MainRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val network = Mockito.mock(NetworkRepository::class.java)
    private val mainRepositoryTest = FakeMainRepository(network)

    private val movieResponse = DataDummy.getDataDummyMovieNetwork()
    private val movieId = movieResponse[0].id
    private val tvResponse = DataDummy.getDataDummyTvshowNetwork()
    private val tvDetailResponse = DataDummy.getDataDummyTvShowDetailNetwork()
    private val tvId = tvResponse[0].id

    @Test
    fun movie() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as NetworkRepository.MovieCallback)
                .movieResponseCallback(movieResponse)
            null
        }.`when`(network).getMovieDb(any())
        val movieData = LiveDataTestUnit.getValue(mainRepositoryTest.movie())
        verify(network).getMovieDb(any())
        assertNotNull(movieData)
        assertEquals(movieResponse.size, movieData.size)
    }

    @Test
    fun movieDetail() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[1] as NetworkRepository.MovieDetailCallback)
                .movieDetailResponseCallback(movieResponse[1])
            null
        }.`when`(network).getMovieDetailDb(eq(movieId), any())
        val movieDataDetail = LiveDataTestUnit.getValue(mainRepositoryTest.movieDetail(movieId))
        verify(network).getMovieDetailDb(eq(movieId), any())
        assertNotNull(movieDataDetail)
        assertEquals(movieResponse[1].title, movieDataDetail.title)
    }

    @Test
    fun tvShow() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as NetworkRepository.TvShowCallback)
                .tvResponseCallback(tvResponse)
            null
        }.`when`(network).getTvShowDb(any())
        val tvData = LiveDataTestUnit.getValue(mainRepositoryTest.tvShow())
        verify(network).getTvShowDb(any())
        assertNotNull(tvData)
        assertEquals(tvResponse.size, tvData.size)
    }

    @Test
    fun tvShowDetail() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[1] as NetworkRepository.TvShowDetailCallback)
                .tvDetailResponseCallback(tvDetailResponse)
            null
        }.`when`(network).getTvShowDetailDb(eq(tvId), any())
        val tvDataDetail = LiveDataTestUnit.getValue(mainRepositoryTest.tvShowDetail(tvId))
        verify(network).getTvShowDetailDb(eq(tvId), any())
        assertNotNull(tvDataDetail)
        assertEquals(tvResponse[0].name, tvDataDetail.name)
    }
}