package com.hafidh.cataloguemovie.ui.repository.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hafidh.cataloguemovie.ui.repository.model.local.LocalRepository
import com.hafidh.cataloguemovie.ui.repository.model.local.data.MovieEntity
import com.hafidh.cataloguemovie.ui.repository.model.local.data.TvEntity
import com.hafidh.cataloguemovie.ui.repository.model.remote.ApiResponse
import com.hafidh.cataloguemovie.ui.repository.model.remote.NetworkRepository
import com.hafidh.cataloguemovie.ui.repository.model.remote.data.ItemData
import com.hafidh.cataloguemovie.ui.repository.model.remote.data.ItemDatatv
import com.hafidh.cataloguemovie.utils.AppExecutors
import com.hafidh.cataloguemovie.utils.NetworkBoundResource
import com.hafidh.cataloguemovie.utils.Source

class MainRepository(
    private val remote: NetworkRepository,
    private val local: LocalRepository,
    private val executors: AppExecutors
) : Source {

    override fun movie(): LiveData<List<MovieEntity>> {
        val movieList = MutableLiveData<List<MovieEntity>>()
        remote.getMovieDb(object : NetworkRepository.MovieCallback {
            override fun movieResponseCallback(movieResponse: List<MovieEntity>) {
                val movies = ArrayList<MovieEntity>()
                for (data in movieResponse.indices) {
                    val dataResponse = movieResponse[data]
                    val movie = MovieEntity(
                        id = dataResponse.id,
                        poster = dataResponse.poster,
                        bigPoster = dataResponse.bigPoster,
                        title = dataResponse.title,
                        overview = dataResponse.overview,
                        released = dataResponse.released,
                        rating = dataResponse.rating
                    )
                    movies.add(movie)
                }
                movieList.postValue(movies)
            }

        })
        return movieList
    }

    override fun movieDetail(movieId: Int): LiveData<MovieEntity> {
        val movieDetail = MutableLiveData<MovieEntity>()
        remote.getMovieDetailDb(movieId, object : NetworkRepository.MovieDetailCallback {
            override fun movieDetailResponseCallback(movieDetailResponse: MovieEntity) {
                val movie = MovieEntity(
                    id = movieDetailResponse.id,
                    poster = movieDetailResponse.poster,
                    bigPoster = movieDetailResponse.bigPoster,
                    title = movieDetailResponse.title,
                    overview = movieDetailResponse.overview,
                    released = movieDetailResponse.released,
                    rating = movieDetailResponse.rating
                )
                movieDetail.postValue(movie)
            }

        })
        return movieDetail
    }

    override fun tvShow(): LiveData<List<TvEntity>> {
        val tvList = MutableLiveData<List<TvEntity>>()
        remote.getTvShowDb(object : NetworkRepository.TvShowCallback {
            override fun tvResponseCallback(tvShowResponse: List<TvEntity>) {
                val tv = ArrayList<TvEntity>()
                for (data in tvShowResponse.indices) {
                    val dataResponse = tvShowResponse[data]
                    val dataTv = TvEntity(
                        id = dataResponse.id,
                        poster = dataResponse.poster,
                        bigPoster = dataResponse.bigPoster,
                        name = dataResponse.name,
                        overview = dataResponse.overview,
                        released = dataResponse.released,
                        rating = dataResponse.rating
                    )
                    tv.add(dataTv)
                }
                tvList.postValue(tv)
            }

        })
        return tvList
    }

    override fun tvShowDetail(tvShowId: Int): LiveData<TvEntity> {
        val tvDetail = MutableLiveData<TvEntity>()
        remote.getTvShowDetailDb(tvShowId, object : NetworkRepository.TvShowDetailCallback {
            override fun tvDetailResponseCallback(tvShowDetailResponse: TvEntity) {
                val tv = TvEntity(
                    id = tvShowDetailResponse.id,
                    poster = tvShowDetailResponse.poster,
                    bigPoster = tvShowDetailResponse.bigPoster,
                    name = tvShowDetailResponse.name,
                    overview = tvShowDetailResponse.overview,
                    released = tvShowDetailResponse.released,
                    rating = tvShowDetailResponse.rating
                )
                tvDetail.postValue(tv)
            }
        })
        return tvDetail
    }

    override fun getMovieFromDb(): LiveData<com.hafidh.cataloguemovie.utils.Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<ItemData>>(executors) {
            override fun loadDataFromDB(): LiveData<List<MovieEntity>> = local.getMovies()
            override fun shouldFetch(data: List<MovieEntity>): Boolean = false
            override fun createCall(): LiveData<ApiResponse<List<ItemData>>>? = null
            override fun saveCallResult(data: List<ItemData>) {}

        }.asLiveData()
    }

    override fun getMovie(movieId: Int): LiveData<com.hafidh.cataloguemovie.utils.Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, ItemData>(executors) {
            override fun loadDataFromDB(): LiveData<MovieEntity> =
                local.getMovieById(movieId)

            override fun shouldFetch(data: MovieEntity): Boolean = false
            override fun createCall(): LiveData<ApiResponse<ItemData>>? = null
            override fun saveCallResult(data: ItemData) {}
        }.asLiveData()
    }

    override fun setFavMovie(movie: MovieEntity, isFavorited: Boolean) {
        executors.diskIO().execute {
            local.setFavoriteMovie(movie, isFavorited)
        }
    }

    override fun insertMovies(movies: List<MovieEntity>) {
        executors.diskIO().execute{
            if (local.getMovies().value.isNullOrEmpty())
                local.insertMovies(movies)
        }
    }

    override fun getMovieAsPaged(): LiveData<com.hafidh.cataloguemovie.utils.Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<ItemData>>(executors) {

            override fun loadDataFromDB(): LiveData<PagedList<MovieEntity>> {
                val pagedListConfig = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setPageSize(5)
                    .setMaxSize(20)
                    .build()
                return LivePagedListBuilder(
                    local.getMoviePaged(),
                    pagedListConfig
                ).build()
            }
            override fun shouldFetch(data: PagedList<MovieEntity>): Boolean = false
            override fun createCall(): LiveData<ApiResponse<List<ItemData>>>? = null
            override fun saveCallResult(data: List<ItemData>) {}
        }.asLiveData()
    }

    override fun getTv(): LiveData<com.hafidh.cataloguemovie.utils.Resource<List<TvEntity>>> {
        return object : NetworkBoundResource<List<TvEntity>, List<ItemData>>(executors) {

            override fun loadDataFromDB(): LiveData<List<TvEntity>> = local.getTv()
            override fun shouldFetch(data: List<TvEntity>): Boolean = false
            override fun createCall(): LiveData<ApiResponse<List<ItemData>>>? = null
            override fun saveCallResult(data: List<ItemData>) {}
        }.asLiveData()
    }

    override fun getTvShow(tvShowId: Int): LiveData<com.hafidh.cataloguemovie.utils.Resource<TvEntity>> {
        return object : NetworkBoundResource<TvEntity, ItemDatatv>(executors) {

            override fun loadDataFromDB(): LiveData<TvEntity> = local.getTvShowById(tvShowId)

            override fun shouldFetch(data: TvEntity): Boolean = false

            override fun createCall(): LiveData<ApiResponse<ItemDatatv>>? = null

            override fun saveCallResult(data: ItemDatatv) {}
        }.asLiveData()
    }

    override fun setFavTv(tvShow: TvEntity, isFavorite: Boolean) {
        executors.diskIO().execute {
            local.setFavoriteTvShow(tvShow, isFavorite)
        }
    }

    override fun insertTv(tvShows: List<TvEntity>) {
        executors.diskIO().execute {
            local.insertTvShows(tvShows)
        }
    }

    override fun getTvShowAsPaged(): LiveData<com.hafidh.cataloguemovie.utils.Resource<PagedList<TvEntity>>> {
        return object : NetworkBoundResource<PagedList<TvEntity>, List<ItemData>>(executors) {

            override fun loadDataFromDB(): LiveData<PagedList<TvEntity>> {
                val pagedListConfig = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setPageSize(5)
                    .setMaxSize(20)
                    .build()
                return LivePagedListBuilder(
                    local.getTvShowPaged(),
                    pagedListConfig
                ).build()
            }

            override fun shouldFetch(data: PagedList<TvEntity>): Boolean = false

            override fun createCall(): LiveData<ApiResponse<List<ItemData>>>? = null

            override fun saveCallResult(data: List<ItemData>) {}
        }.asLiveData()
    }

    companion object {
        @Volatile
        private var INSTANCE: MainRepository? = null

        fun getInstance(
            networkRepository: NetworkRepository,
            local: LocalRepository,
            executors: AppExecutors
        ): MainRepository? {
            if (INSTANCE == null) {
                synchronized(MainRepository::class.java) {
                    if (INSTANCE == null)
                        INSTANCE = MainRepository(networkRepository, local, executors)
                }
            }
            return INSTANCE
        }
    }
}
