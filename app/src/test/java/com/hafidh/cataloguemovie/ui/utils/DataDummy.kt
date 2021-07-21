package com.hafidh.cataloguemovie.ui.utils

import com.hafidh.cataloguemovie.ui.repository.model.local.data.MovieEntity
import com.hafidh.cataloguemovie.ui.repository.model.local.data.TvEntity

object DataDummy {
    fun getDataDummyMovieNetwork(): List<MovieEntity> {
        val dataMovie = ArrayList<MovieEntity>()
        dataMovie.add(
            MovieEntity(
                460465,
                "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, " +
                        "Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld " +
                        "in a high stakes battle for the universe.",
                "2021-04-07",
                7.8
            )
        )
        dataMovie.add(MovieEntity(1, "", "", "", "", "", 0.0))
        dataMovie.add(MovieEntity(2, "", "", "", "", "", 0.0))
        dataMovie.add(MovieEntity(3, "", "", "", "", "", 0.0))
        dataMovie.add(MovieEntity(4, "", "", "", "", "", 0.0))
        dataMovie.add(MovieEntity(5, "", "", "", "", "", 0.0))
        dataMovie.add(MovieEntity(6, "", "", "", "", "", 0.0))
        dataMovie.add(MovieEntity(7, "", "", "", "", "", 0.0))
        dataMovie.add(MovieEntity(8, "", "", "", "", "", 0.0))
        dataMovie.add(MovieEntity(9, "", "", "", "", "", 0.0))
        dataMovie.add(MovieEntity(10, "", "", "", "", "", 0.0))
        dataMovie.add(MovieEntity(11, "", "", "", "", "", 0.0))
        dataMovie.add(MovieEntity(12, "", "", "", "", "", 0.0))
        dataMovie.add(MovieEntity(13, "", "", "", "", "", 0.0))
        dataMovie.add(MovieEntity(14, "", "", "", "", "", 0.0))
        dataMovie.add(MovieEntity(15, "", "", "", "", "", 0.0))
        dataMovie.add(MovieEntity(16, "", "", "", "", "", 0.0))
        dataMovie.add(MovieEntity(17, "", "", "", "", "", 0.0))
        dataMovie.add(MovieEntity(18, "", "", "", "", "", 0.0))
        dataMovie.add(MovieEntity(19, "", "", "", "", "", 0.0))

        return dataMovie
    }

    fun getDataDummyTvshowNetwork(): List<TvEntity> {
        val dataTvshow = ArrayList<TvEntity>()
        dataTvshow.add(
            TvEntity(
                88396,
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, " +
                        "Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "2021-03-19",
                7.9
            )
        )
        dataTvshow.add(TvEntity(1, "", "", "", "", "", 0.0))
        dataTvshow.add(TvEntity(2, "", "", "", "", "", 0.0))
        dataTvshow.add(TvEntity(3, "", "", "", "", "", 0.0))
        dataTvshow.add(TvEntity(4, "", "", "", "", "", 0.0))
        dataTvshow.add(TvEntity(5, "", "", "", "", "", 0.0))
        dataTvshow.add(TvEntity(6, "", "", "", "", "", 0.0))
        dataTvshow.add(TvEntity(7, "", "", "", "", "", 0.0))
        dataTvshow.add(TvEntity(8, "", "", "", "", "", 0.0))
        dataTvshow.add(TvEntity(9, "", "", "", "", "", 0.0))
        dataTvshow.add(TvEntity(10, "", "", "", "", "", 0.0))
        dataTvshow.add(TvEntity(11, "", "", "", "", "", 0.0))
        dataTvshow.add(TvEntity(12, "", "", "", "", "", 0.0))
        dataTvshow.add(TvEntity(13, "", "", "", "", "", 0.0))
        dataTvshow.add(TvEntity(14, "", "", "", "", "", 0.0))
        dataTvshow.add(TvEntity(15, "", "", "", "", "", 0.0))
        dataTvshow.add(TvEntity(16, "", "", "", "", "", 0.0))
        dataTvshow.add(TvEntity(17, "", "", "", "", "", 0.0))
        dataTvshow.add(TvEntity(18, "", "", "", "", "", 0.0))
        dataTvshow.add(TvEntity(19, "", "", "", "", "", 0.0))

        return dataTvshow
    }

    fun getDataDummyTvShowDetailNetwork(): TvEntity =
        TvEntity(
            88396,
            "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
            "The Falcon and the Winter Soldier",
            "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, " +
                    "Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            "2021-03-19",
            7.9
        )

    fun getDataDummyMovieDetailNetwork(): MovieEntity =
        MovieEntity(
            460465,
            "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
            "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
            "Mortal Kombat",
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, " +
                    "Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld " +
                    "in a high stakes battle for the universe.",
            "2021-04-07",
            7.8
        )

    fun getMovie(id: Int): MovieEntity? {
        for (i in getDataDummyMovieNetwork()){
            if (id == i.id)
                return i
        }
        return null
    }

    fun getTv(id: Int): TvEntity? {
        for (i in getDataDummyTvshowNetwork()) {
            if (id == i.id)
                return i
        }
        return null
    }
}


