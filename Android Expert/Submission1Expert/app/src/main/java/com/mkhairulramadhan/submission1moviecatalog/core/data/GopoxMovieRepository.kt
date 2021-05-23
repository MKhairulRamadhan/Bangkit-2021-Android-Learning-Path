package com.mkhairulramadhan.submission1moviecatalog.core.data

import com.mkhairulramadhan.submission1moviecatalog.core.data.local.LocalDataSource
import com.mkhairulramadhan.submission1moviecatalog.core.data.remote.ApiResponse
import com.mkhairulramadhan.submission1moviecatalog.core.data.remote.MovieDataItem
import com.mkhairulramadhan.submission1moviecatalog.core.data.remote.RemoteDataSource
import com.mkhairulramadhan.submission1moviecatalog.core.data.remote.TvDataItem
import com.mkhairulramadhan.submission1moviecatalog.core.domain.model.MovieModel
import com.mkhairulramadhan.submission1moviecatalog.core.domain.model.TvModel
import com.mkhairulramadhan.submission1moviecatalog.core.domain.repository.IGopoxMovieRepository
import com.mkhairulramadhan.submission1moviecatalog.core.utils.DataMapper
import com.mkhairulramadhan.submission1moviecatalog.core.utils.ExecutorApp
import com.mkhairulramadhan.submission1moviecatalog.core.valueObject.ResourceData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GopoxMovieRepository(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource, private val executorApp: ExecutorApp) : IGopoxMovieRepository {

    companion object{
        @Volatile
        private var instance: GopoxMovieRepository? = null
        fun getInstance(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, executorApp: ExecutorApp): GopoxMovieRepository =
                instance ?: synchronized(this){
                    instance ?: GopoxMovieRepository(remoteDataSource, localDataSource, executorApp)
                }
    }

    override fun getAllMovie(): Flow<ResourceData<List<MovieModel>>> {
        return object : NetworkBoundResource<List<MovieModel>, List<MovieDataItem>>(executorApp){
            override fun fromDBLoad(): Flow<List<MovieModel>> {
                return localDataSource.getAllMovie().map { DataMapper.mapMovieEntityToMovieModel(it) }
            }
            override fun shouldFetch(data: List<MovieModel>?): Boolean =
                data == null || data.isEmpty()
            override suspend fun createCall(): Flow<ApiResponse<List<MovieDataItem>>> =
                remoteDataSource.getAllMovie()
            override suspend fun saveCallResult(data: List<MovieDataItem>) {
                val listMovie = DataMapper.mapMovieDataItemToMovieEntities(data)
                localDataSource.insertMovie(listMovie)
            }
        }.asFlow()
    }

    override fun getDetailMovie(id: Int): Flow<ResourceData<MovieModel>> {
        return object : NetworkBoundResource<MovieModel, MovieDataItem>(executorApp){
            override fun fromDBLoad(): Flow<MovieModel> {
                return localDataSource.getDetailMovie(id).map{
                    DataMapper.mapMovieEntityToMovieDetail(it)
                }
            }
            override fun shouldFetch(data: MovieModel?): Boolean =
                data == null
            override suspend fun createCall(): Flow<ApiResponse<MovieDataItem>> =
                remoteDataSource.getDetailMovie(id)
            override suspend fun saveCallResult(data: MovieDataItem) {
                val movie = DataMapper.mapMovieDataItemToMovieEntitiesDetail(data)
                localDataSource.insertDetailMovie(movie)
            }
        }.asFlow()
    }

    override fun getAllTv(): Flow<ResourceData<List<TvModel>>> {
        return object: NetworkBoundResource<List<TvModel>, List<TvDataItem>>(executorApp){
            override fun fromDBLoad(): Flow<List<TvModel>> {
                return localDataSource.getAllTv().map{
                    DataMapper.mapTvEntityToTvModel(it)
                }
            }

            override fun shouldFetch(data: List<TvModel>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvDataItem>>> =
                remoteDataSource.getAllTv()

            override suspend fun saveCallResult(data: List<TvDataItem>) {
                val listTv = DataMapper.mapTvDataItemToTvEntities(data)
                localDataSource.insertTv(listTv)
            }
        }.asFlow()
    }

    override fun getDetailTv(id: Int): Flow<ResourceData<TvModel>> {
        return object : NetworkBoundResource<TvModel, TvDataItem>(executorApp){
            override fun fromDBLoad(): Flow<TvModel> {
                return localDataSource.getDetailTv(id).map{
                    DataMapper.mapTvEntityToTvModelDetail(it)
                }
            }
            override fun shouldFetch(data: TvModel?): Boolean =
                data == null
            override suspend fun createCall(): Flow<ApiResponse<TvDataItem>> =
                remoteDataSource.getDetailTv(id)
            override suspend fun saveCallResult(data: TvDataItem) {
                val tv = DataMapper.mapTvDataItemToTvEntitiesDetail(data)
                localDataSource.insertDetailTv(tv)
            }
        }.asFlow()
    }

    override fun setMovieFavorite(movie: MovieModel, state: Boolean) {
        val dataMovie = DataMapper.mapMovieModelToMovieEntity(movie)
        executorApp.diskIO().execute{localDataSource.setFavoriteMovie(dataMovie, state)}
    }

    override fun  getFavoriteMovie(): Flow<List<MovieModel>> {
        return localDataSource.getFavoriteMovie().map{
            DataMapper.mapMovieEntityToMovieModel(it)
        }
    }

    override fun setTvFavorite(tv: TvModel, state: Boolean) {
        val dataTv = DataMapper.mapTvModelToTvEntity(tv)
        executorApp.diskIO().execute{localDataSource.setFavoriteTv(dataTv, state)}
    }

    override fun getFavoriteTv(): Flow<List<TvModel>> {
        return localDataSource.getFavoriteTv().map{
            DataMapper.mapTvEntityToTvModel(it)
        }
    }

}