package com.hafidh.cataloguemovie.utils

import com.hafidh.cataloguemovie.ui.repository.model.local.data.MovieEntity
import com.hafidh.cataloguemovie.ui.repository.model.local.data.TvEntity
import com.hafidh.cataloguemovie.ui.repository.model.remote.data.ItemData
import com.hafidh.cataloguemovie.ui.repository.model.remote.data.ItemDatatv

object DataHelper {


    fun mappingToResponse(input: List<ItemData>): List<MovieEntity> =
        input.map {
            MovieEntity(
                id = it.id,
                poster = it.poster,
                bigPoster = it.bigPoster,
                title = it.title,
                overview = it.overview,
                released = it.released,
                rating = it.rating
            )
        }

    fun mappingDetail(input: ItemData) = MovieEntity(
        id = input.id,
        poster = input.poster,
        bigPoster = input.bigPoster,
        title = input.title,
        overview = input.overview,
        released = input.released,
        rating = input.rating
    )

    fun mappingTv(input: List<ItemData>): List<TvEntity> =
        input.map {
            TvEntity(
                id = it.id,
                poster = it.poster,
                bigPoster = it.bigPoster,
                name = it.name,
                overview = it.overview,
                released = it.released,
                rating = it.rating
            )
        }

    fun mappingDetail(input: ItemDatatv) = TvEntity(
        id = input.id,
        poster = input.poster,
        bigPoster = input.bigPoster,
        name = input.name,
        overview = input.overview,
        released = input.released,
        rating = input.rating
    )
}