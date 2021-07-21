package com.hafidh.cataloguemovie.ui.repository.model.local.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_table")
data class TvEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "posterPath")
    val poster: String?,

    @ColumnInfo(name = "backdropPath")
    val bigPoster: String?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "releaseDate")
    val released: String?,

    @ColumnInfo(name = "voteAverage")
    val rating: Double?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite : Boolean = false
)