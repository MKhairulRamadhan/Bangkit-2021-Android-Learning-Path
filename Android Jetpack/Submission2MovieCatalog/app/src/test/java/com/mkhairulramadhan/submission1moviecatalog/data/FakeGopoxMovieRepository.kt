package com.mkhairulramadhan.submission1moviecatalog.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mkhairulramadhan.submission1moviecatalog.data.remote.Genre
import com.mkhairulramadhan.submission1moviecatalog.data.remote.MovieDataItem
import com.mkhairulramadhan.submission1moviecatalog.data.remote.RemoteDataSource
import com.mkhairulramadhan.submission1moviecatalog.data.remote.TvDataItem
import com.mkhairulramadhan.submission1moviecatalog.model.MovieTvModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FakeGopoxMovieRepository(private val remoteDataSource: RemoteDataSource): GopoxMovieDataSource {

    override fun getAllMovie(): LiveData<List<MovieTvModel>> {
        val list = MutableLiveData<List<MovieTvModel>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getAllMovie(object : RemoteDataSource.CustomGetAllMovieCallback{
                override fun onResponse(movieResponse: List<MovieDataItem>) {
                    val movieList = ArrayList<MovieTvModel>()
                    for(response in movieResponse){
                        val movie = MovieTvModel(
                                id = response.id,
                                title = response.title,
                                backDropImage = response.backdrop_path,
                                posterImage = response.poster_path,
                                year = response.release_date?.dateToYear(),
                                star = response.vote_average.toString(),
                                language = response.original_language,
                                synopsis = response.overview
                        )
                        movieList.add(movie)
                    }
                    list.postValue(movieList)
                }
            })
        }
        return list
    }

    override fun getDetailMovie(id: Int): LiveData<MovieTvModel> {
        val list = MutableLiveData<MovieTvModel>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getDetailMovie(id, object : RemoteDataSource.CustomDetailMovieCallback {
                override fun onResponse(movieResponse: MovieDataItem) {
                    val movie = MovieTvModel(
                            id = movieResponse.id,
                            title = movieResponse.title,
                            backDropImage = movieResponse.backdrop_path,
                            posterImage = movieResponse.poster_path,
                            year = movieResponse.release_date?.dateToYear(),
                            star = movieResponse.vote_average.toString(),
                            language = movieResponse.original_language,
                            duration = movieResponse.runtime?.toHourMinute(),
                            synopsis = movieResponse.overview,
                            tag = concatGenre(movieResponse.genres)
                    )
                    list.postValue(movie)
                }

            })
        }
        return list
    }

    override fun getAllTv(): LiveData<List<MovieTvModel>> {
        val list = MutableLiveData<List<MovieTvModel>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getAllTv(object : RemoteDataSource.CustomGetAllTvCallback {
                override fun onResponse(tvResponse: List<TvDataItem>) {
                    val tvList = ArrayList<MovieTvModel>()
                    for (response in tvResponse) {
                        val movie = MovieTvModel(
                                id = response.id,
                                title = response.name,
                                backDropImage = response.backdrop_path,
                                posterImage = response.poster_path,
                                year = response.first_air_date?.dateToYear(),
                                star = response.vote_average.toString(),
                                language = response.original_language,
                                synopsis = response.overview
                        )
                        tvList.add(movie)
                    }
                    list.postValue(tvList)
                }

            })
        }
        return list
    }

    override fun getDetailTv(id: Int): LiveData<MovieTvModel> {
        val list = MutableLiveData<MovieTvModel>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getDetailTv(id, object : RemoteDataSource.CustomDetailTvCallback {
                override fun onResponse(tvResponse: TvDataItem) {
                    val tv = MovieTvModel(
                            id = tvResponse.id,
                            title = tvResponse.name,
                            backDropImage = tvResponse.backdrop_path,
                            posterImage = tvResponse.poster_path,
                            year = tvResponse.first_air_date?.dateToYear(),
                            star = tvResponse.vote_average.toString(),
                            language = tvResponse.original_language,
                            duration = tvResponse.episode_run_time?.first()?.toHourMinute(),
                            synopsis = tvResponse.overview,
                            tag = concatGenre(tvResponse.genres)
                    )
                    list.postValue(tv)
                }

            })
        }
        return list
    }

    private fun concatGenre(genres: List<Genre>?): String{
        var tag = ""
        if (genres != null) {
            for (genre in genres){
                tag += "${genre.name}, "
            }
        }
        return tag
    }

    private fun String.dateToYear(): String{
        return this.split("-").toTypedArray()[0]
    }

    private fun Int.toHourMinute(): String{
        return "${this / 60}h ${this % 60}min"
    }
}