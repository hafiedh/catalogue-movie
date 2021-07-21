package com.hafidh.cataloguemovie.utils

import com.hafidh.cataloguemovie.ui.Repository.model.remote.ItemData
import com.hafidh.cataloguemovie.ui.Repository.model.remote.ItemDatatv

object DataDummy {
    fun getDataDummyMovieNetwork() : ItemData =
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


