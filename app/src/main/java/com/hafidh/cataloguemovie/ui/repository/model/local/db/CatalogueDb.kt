package com.hafidh.cataloguemovie.ui.repository.model.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hafidh.cataloguemovie.ui.repository.model.local.data.MovieEntity
import com.hafidh.cataloguemovie.ui.repository.model.local.data.TvEntity

@Database(
    entities = [MovieEntity::class,TvEntity::class],
    version = 5,
    exportSchema = false
)
abstract class CatalogueDb : RoomDatabase(){
    abstract fun dao() : Dao
    companion object {
        @Volatile
        private var INSTANCE: CatalogueDb? = null

        fun getInstance(context: Context): CatalogueDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: Room.databaseBuilder(context.applicationContext, CatalogueDb::class.java, "Catalogue.db").fallbackToDestructiveMigration().build()
            }
    }
}