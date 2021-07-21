package com.hafidh.cataloguemovie.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.hafidh.cataloguemovie.ui.repository.model.MainRepository
import com.hafidh.cataloguemovie.ui.repository.model.local.data.MovieEntity
import com.hafidh.cataloguemovie.ui.repository.model.local.data.TvEntity
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
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvshowViewModelTest {
    private lateinit var viewModel: TvshowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mainRepository: MainRepository

    @Mock
    private lateinit var observer: Observer<List<TvEntity>>

    @Mock
    private lateinit var observerTvDetail: Observer<TvEntity>

    @Mock
    private lateinit var observerDb: Observer<Resource<TvEntity>>

    @Mock
    private lateinit var observerMoviePaged: Observer<Resource<PagedList<TvEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvEntity>


    @Before
    fun setUp() {
        viewModel = TvshowViewModel(mainRepository)
    }


    @Test
    fun getTvshows() {
        val dataDummy = DataDummy.getDataDummyTvshowNetwork()
        val tv = MutableLiveData<List<TvEntity>>()
        tv.value = dataDummy
        `when`(mainRepository.tvShow()).thenReturn(tv)
        val entities = viewModel.getTv().value
        assertNotNull(entities)
        assertEquals(20, entities?.size)

        viewModel.getTv().observeForever(observer)
        Mockito.verify(observer).onChanged(dataDummy)
    }

    @Test
    fun detail() {
        val dummy = DataDummy.getDataDummyTvShowDetailNetwork()
        val tvDetail = MutableLiveData<TvEntity>()
        tvDetail.value = dummy
        `when`(mainRepository.tvShowDetail(tvDetail.value!!.id)).thenReturn(tvDetail)
        val detail = viewModel.getDetail(tvDetail.value!!.id)
        assertNotNull(detail)
        assertEquals(
            tvDetail.value?.id,
            viewModel.getDetail(tvDetail.value!!.id).value?.id
        )
        assertEquals(
            tvDetail.value?.name,
            tvDetail.value?.id?.let { viewModel.getDetail(it).value?.name }
        )
        assertEquals(
            tvDetail.value?.overview,
            tvDetail.value?.id?.let { viewModel.getDetail(it).value?.overview }
        )
        assertEquals(
            tvDetail.value?.poster,
            tvDetail.value?.id?.let { viewModel.getDetail(it).value?.poster }
        )
        assertEquals(
            tvDetail.value?.released,
            tvDetail.value?.id?.let { viewModel.getDetail(it).value?.released }
        )
        assertEquals(
            tvDetail.value?.rating,
            tvDetail.value?.id?.let { viewModel.getDetail(it).value?.rating }
        )
        tvDetail.value?.id?.let { viewModel.getDetail(it).observeForever(observerTvDetail) }
        verify(observerTvDetail).onChanged(dummy)
    }

    @Test
    fun getDetail() {
        val dummy = Resource.success(DataDummy.getDataDummyTvShowDetailNetwork())
        val movieDetail = MutableLiveData<Resource<TvEntity>>()
        movieDetail.value = dummy
        `when`(movieDetail.value?.data?.id?.let { mainRepository.getTvShow(it) })
            .thenReturn(movieDetail)
        viewModel.getTvSwitch.observeForever(observerDb)
        val result =
            LiveDataTestUnit.getValue(mainRepository.getTvShow(movieDetail.value?.data!!.id))
        assertNotNull(result)
    }

    @Test
    fun insertFavorite() {
        val dummy = DataDummy.getDataDummyTvshowNetwork()
        viewModel.insert(dummy)
        verify(mainRepository).insertTv(dummy)
        assertNotNull(dummy)
    }

    @Test
    fun tvShowPaged() {
        val dummy = Resource.success(pagedList)
        `when`(dummy.data?.size).thenReturn(4)
        val tv = MutableLiveData<Resource<PagedList<TvEntity>>>()
        tv.value = dummy
        `when`(mainRepository.getTvShowAsPaged()).thenReturn(tv)
        val result = viewModel.tvPaged().value?.data
        verify(mainRepository).getTvShowAsPaged()
        assertNotNull(result)
        assertEquals(4 , result?.size)
        viewModel.tvPaged().observeForever(observerMoviePaged)
        verify(observerMoviePaged).onChanged(dummy)
    }


}