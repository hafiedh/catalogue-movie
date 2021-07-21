package com.hafidh.cataloguemovie.di

import android.content.Context
import com.hafidh.cataloguemovie.api.Config
import com.hafidh.cataloguemovie.ui.Repository.model.MainRepository
import com.hafidh.cataloguemovie.ui.Repository.model.remote.NetworkRepository

object Inject {
    fun mainRepository(context: Context): MainRepository{
        val remoteRepository = NetworkRepository.getInstance(Config)
        return MainRepository.getInstance(remoteRepository)!!
    }
}