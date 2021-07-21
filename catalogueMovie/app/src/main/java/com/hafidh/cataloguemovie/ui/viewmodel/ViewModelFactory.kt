package com.hafidh.cataloguemovie.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hafidh.cataloguemovie.di.Inject
import com.hafidh.cataloguemovie.ui.Repository.model.MainRepository


class ViewModelFactory(private val repo: MainRepository): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> MovieViewModel(repo) as T
            modelClass.isAssignableFrom(TvshowViewModel::class.java) -> TvshowViewModel(repo) as T
            else -> throw IllegalArgumentException("Unknown ViewModel: " + modelClass.name)
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(Inject.mainRepository(context))
            }

    }
}