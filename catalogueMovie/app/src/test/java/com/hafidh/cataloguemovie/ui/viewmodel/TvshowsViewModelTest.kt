package com.hafidh.cataloguemovie.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hafidh.cataloguemovie.ui.Repository.model.DataDummy
import com.hafidh.cataloguemovie.ui.Repository.model.MainRepository
import com.hafidh.cataloguemovie.ui.Repository.model.remote.ItemData
import com.hafidh.cataloguemovie.ui.Repository.model.remote.ItemDatatv
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvshowsViewModelTest {

    private lateinit var viewModel: TvshowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mainRepository: MainRepository

    @Mock
    private lateinit var observer: Observer<List<ItemData>>
    
    @Mock
    private lateinit var observerTvDetail: Observer<ItemDatatv>

    @Before
    fun setUp() {
        viewModel = TvshowViewModel(mainRepository)
    }


    @Test
    fun getTvshows() {
        val dataDummy = DataDummy.getDataDummyTvshowNetwork()
        val tv = MutableLiveData<List<ItemData>>()
        tv.value = dataDummy
        Mockito.`when`(mainRepository.tvShow()).thenReturn(tv)
        val entities = viewModel.getTv().value
        Assert.assertNotNull(entities)
        Assert.assertEquals(20, entities?.size)

        viewModel.getTv().observeForever(observer)
        Mockito.verify(observer).onChanged(dataDummy)
    }

    @Test
    fun detail() {
        val dummy = DataDummy.getDataDummyTvShowDetailNetwork()
        val tvDetail = MutableLiveData<ItemDatatv>()
        tvDetail.value = dummy
        Mockito.`when`(mainRepository.tvShowDetail(tvDetail.value!!.id)).thenReturn(tvDetail)
        val detail = viewModel.getDetail(tvDetail.value!!.id)
        Assert.assertNotNull(detail)
        Assert.assertEquals(
            tvDetail.value!!.id,
            viewModel.getDetail(tvDetail.value!!.id).value?.id
        )
        Assert.assertEquals(
            tvDetail.value!!.name,
            viewModel.getDetail(tvDetail.value!!.id).value?.name
        )
        Assert.assertEquals(
            tvDetail.value!!.overview,
            viewModel.getDetail(tvDetail.value!!.id).value?.overview
        )
        Assert.assertEquals(
            tvDetail.value!!.poster,
            viewModel.getDetail(tvDetail.value!!.id).value?.poster
        )
        Assert.assertEquals(
            tvDetail.value!!.released,
            viewModel.getDetail(tvDetail.value!!.id).value?.released
        )
        Assert.assertEquals(
            tvDetail.value!!.rating,
            viewModel.getDetail(tvDetail.value!!.id).value?.rating
        )
        viewModel.getDetail(tvDetail.value!!.id).observeForever(observerTvDetail)
        verify(observerTvDetail).onChanged(dummy)
    }

}