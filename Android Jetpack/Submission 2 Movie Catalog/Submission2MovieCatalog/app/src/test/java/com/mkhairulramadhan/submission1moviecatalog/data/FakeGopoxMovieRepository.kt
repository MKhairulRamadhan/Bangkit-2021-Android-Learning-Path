package com.mkhairulramadhan.submission1moviecatalog.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mkhairulramadhan.submission1moviecatalog.data.local.LocalDataSource
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.MovieEntity
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.TvEntity
import com.mkhairulramadhan.submission1moviecatalog.data.remote.ApiResponse
import com.mkhairulramadhan.submission1moviecatalog.data.remote.MovieDataItem
import com.mkhairulramadhan.submission1moviecatalog.data.remote.RemoteDataSource
import com.mkhairulramadhan.submission1moviecatalog.data.remote.TvDataItem
import com.mkhairulramadhan.submission1moviecatalog.utils.ExecutorApp
import com.mkhairulramadhan.submission1moviecatalog.valueObject.ResourceData

class FakeGopoxMovieRepository(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource, private val executorApp: ExecutorApp) : GopoxMovieDataSource {

    override fun getAllMovie(): LiveData<ResourceData<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieDataItem>>(executorApp){
            override fun fromDBLoad(): LiveData<PagedList<MovieEntity>> {
                val configuration = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                return LivePagedListBuilder(localDataSource.getAllMovie(), configuration).build()
            }
            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                    data == null || data.isEmpty()
            override fun createCall(): LiveData<ApiResponse<List<MovieDataItem>>> =
                    remoteDataSource.getAllMovie()
            override fun saveCallResult(data: List<MovieDataItem>) {
                val listMovie = ArrayList<MovieEntity>()
                for(res in data){
                    val movie = MovieEntity(res.id,
                            res.title,
                            res.backdrop_path,
                            res.poster_path,
                            res.release_date?.dateToYear(),
                            res.vote_average.toString(),
                            res.original_language,
                            res.overview,
                            false
                    )
                    listMovie.add(movie)
                }
                localDataSource.insertMovie(listMovie)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(id: Int): LiveData<ResourceData<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDataItem>(executorApp){
            override fun fromDBLoad(): LiveData<MovieEntity> =
                    localDataSource.getDetailMovie(id)
            override fun shouldFetch(data: MovieEntity?): Boolean =
                    data == null
            override fun createCall(): LiveData<ApiResponse<MovieDataItem>> =
                    remoteDataSource.getDetailMovie(id)
            override fun saveCallResult(data: MovieDataItem) {
                val movie = MovieEntity(data.id,
                        data.title,
                        data.backdrop_path,
                        data.poster_path,
                        data.release_date?.dateToYear(),
                        data.vote_average.toString(),
                        data.original_language,
                        data.overview,
                        false
                )
                localDataSource.insertDetailMovie(movie)
            }
        }.asLiveData()
    }

    override fun getAllTv(): LiveData<ResourceData<PagedList<TvEntity>>> {
        return object: NetworkBoundResource<PagedList<TvEntity>, List<TvDataItem>>(executorApp){
            override fun fromDBLoad(): LiveData<PagedList<TvEntity>> {
                val configuration = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                return LivePagedListBuilder(localDataSource.getAllTv(), configuration).build()
            }

            override fun shouldFetch(data: PagedList<TvEntity>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvDataItem>>> =
                    remoteDataSource.getAllTv()

            override fun saveCallResult(data: List<TvDataItem>) {
                val listTv = ArrayList<TvEntity>()
                for(res in data){
                    val tv = TvEntity(res.id,
                            res.name,
                            res.backdrop_path,
                            res.poster_path,
                            res.first_air_date?.dateToYear(),
                            res.vote_average.toString(),
                            res.original_language,
                            res.overview,
                            false
                    )
                    listTv.add(tv)
                }
                localDataSource.insertTv(listTv)
            }
        }.asLiveData()
    }

    override fun getDetailTv(id: Int): LiveData<ResourceData<TvEntity>> {
        return object : NetworkBoundResource<TvEntity, TvDataItem>(executorApp){
            override fun fromDBLoad(): LiveData<TvEntity> =
                    localDataSource.getDetailTv(id)
            override fun shouldFetch(data: TvEntity?): Boolean =
                    data == null
            override fun createCall(): LiveData<ApiResponse<TvDataItem>> =
                    remoteDataSource.getDetailTv(id)
            override fun saveCallResult(data: TvDataItem) {
                val tv = TvEntity(data.id,
                        data.name,
                        data.backdrop_path,
                        data.poster_path,
                        data.first_air_date?.dateToYear(),
                        data.vote_average.toString(),
                        data.original_language,
                        data.overview,
                        false
                )
                localDataSource.insertDetailTv(tv)
            }
        }.asLiveData()
    }

    override fun setMovieFavorite(movie: MovieEntity, state: Boolean) =
            executorApp.diskIO().execute{localDataSource.setFavoriteMovie(movie, state)}

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        val configuration = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), configuration).build()
    }

    override fun setTvFavorite(tv: TvEntity, state: Boolean) =
            executorApp.diskIO().execute{localDataSource.setFavoriteTv(tv, state)}

    override fun getFavoriteTv(): LiveData<PagedList<TvEntity>> {
        val configuration = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTv(), configuration).build()
    }

    private fun String.dateToYear(): String{
        return this.split("-").toTypedArray()[0]
    }

}