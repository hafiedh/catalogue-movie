package com.hafidh.cataloguemovie.di

import android.content.Context
import com.hafidh.cataloguemovie.api.Config
import com.hafidh.cataloguemovie.ui.repository.model.MainRepository
import com.hafidh.cataloguemovie.ui.repository.model.local.LocalRepository
import com.hafidh.cataloguemovie.ui.repository.model.local.db.CatalogueDb
import com.hafidh.cataloguemovie.ui.repository.model.remote.NetworkRepository
import com.hafidh.cataloguemovie.utils.AppExecutors

object Inject {
    fun mainRepository(context: Context): MainRepository{
        val remoteRepository = NetworkRepository.getInstance(Config)
        val localRepository = LocalRepository(CatalogueDb.getInstance(context).dao())
        val executors = AppExecutors()
        return MainRepository.getInstance(remoteRepository,localRepository,executors)!!
    }
}