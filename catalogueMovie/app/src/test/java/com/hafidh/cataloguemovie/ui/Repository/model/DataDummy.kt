package com.hafidh.cataloguemovie.ui.Repository.model

import com.hafidh.cataloguemovie.ui.Repository.model.remote.ItemData
import com.hafidh.cataloguemovie.ui.Repository.model.remote.ItemDatatv

object DataDummy {
    fun getDataDummyMovieNetwork(): List<ItemData> {
        val dataMovie = ArrayList<ItemData>()
        dataMovie.add(
            ItemData(
                460465,
                "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, " +
                        "Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld " +
                        "in a high stakes battle for the universe.",
                "2021-04-07",
                7.8,
                ""
            )
        )
        dataMovie.add(ItemData(1, "", "", "", "", "", 0.0, ""))
        dataMovie.add(ItemData(2, "", "", "", "", "", 0.0, ""))
        dataMovie.add(ItemData(3, "", "", "", "", "", 0.0, ""))
        dataMovie.add(ItemData(4, "", "", "", "", "", 0.0, ""))
        dataMovie.add(ItemData(5, "", "", "", "", "", 0.0, ""))
        dataMovie.add(ItemData(6, "", "", "", "", "", 0.0, ""))
        dataMovie.add(ItemData(7, "", "", "", "", "", 0.0, ""))
        dataMovie.add(ItemData(8, "", "", "", "", "", 0.0, ""))
        dataMovie.add(ItemData(9, "", "", "", "", "", 0.0, ""))
        dataMovie.add(ItemData(10, "", "", "", "", "", 0.0, ""))
        dataMovie.add(ItemData(11, "", "", "", "", "", 0.0, ""))
        dataMovie.add(ItemData(12, "", "", "", "", "", 0.0, ""))
        dataMovie.add(ItemData(13, "", "", "", "", "", 0.0, ""))
        dataMovie.add(ItemData(14, "", "", "", "", "", 0.0, ""))
        dataMovie.add(ItemData(15, "", "", "", "", "", 0.0, ""))
        dataMovie.add(ItemData(16, "", "", "", "", "", 0.0, ""))
        dataMovie.add(ItemData(17, "", "", "", "", "", 0.0, ""))
        dataMovie.add(ItemData(18, "", "", "", "", "", 0.0, ""))
        dataMovie.add(ItemData(19, "", "", "", "", "", 0.0, ""))

        return dataMovie
    }

    fun getDataDummyTvshowNetwork(): List<ItemData> {
        val dataTvshow = ArrayList<ItemData>()
        dataTvshow.add(
            ItemData(
                88396,
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, " +
                        "Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "2021-03-19",
                7.9,
                "The Falcon and the Winter Soldier"
            )
        )
        dataTvshow.add(ItemData(1, "", "", "", "", "", 0.0, ""))
        dataTvshow.add(ItemData(2, "", "", "", "", "", 0.0, ""))
        dataTvshow.add(ItemData(3, "", "", "", "", "", 0.0, ""))
        dataTvshow.add(ItemData(4, "", "", "", "", "", 0.0, ""))
        dataTvshow.add(ItemData(5, "", "", "", "", "", 0.0, ""))
        dataTvshow.add(ItemData(6, "", "", "", "", "", 0.0, ""))
        dataTvshow.add(ItemData(7, "", "", "", "", "", 0.0, ""))
        dataTvshow.add(ItemData(8, "", "", "", "", "", 0.0, ""))
        dataTvshow.add(ItemData(9, "", "", "", "", "", 0.0, ""))
        dataTvshow.add(ItemData(10, "", "", "", "", "", 0.0, ""))
        dataTvshow.add(ItemData(11, "", "", "", "", "", 0.0, ""))
        dataTvshow.add(ItemData(12, "", "", "", "", "", 0.0, ""))
        dataTvshow.add(ItemData(13, "", "", "", "", "", 0.0, ""))
        dataTvshow.add(ItemData(14, "", "", "", "", "", 0.0, ""))
        dataTvshow.add(ItemData(15, "", "", "", "", "", 0.0, ""))
        dataTvshow.add(ItemData(16, "", "", "", "", "", 0.0, ""))
        dataTvshow.add(ItemData(17, "", "", "", "", "", 0.0, ""))
        dataTvshow.add(ItemData(18, "", "", "", "", "", 0.0, ""))
        dataTvshow.add(ItemData(19, "", "", "", "", "", 0.0, ""))

        return dataTvshow
    }

    fun getDataDummyTvShowDetailNetwork(): ItemDatatv =
        ItemDatatv(
            88396,
            "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
            "The Falcon and the Winter Soldier",
            "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, " +
                    "Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            "2021-03-19",
            7.9,
        )
}


