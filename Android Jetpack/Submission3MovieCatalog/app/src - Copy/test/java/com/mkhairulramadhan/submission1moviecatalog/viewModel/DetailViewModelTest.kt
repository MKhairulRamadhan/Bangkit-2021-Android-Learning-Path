package com.mkhairulramadhan.submission1moviecatalog.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mkhairulramadhan.submission1moviecatalog.data.GopoxMovieRepository
import com.mkhairulramadhan.submission1moviecatalog.model.MovieTvModel
import com.mkhairulramadhan.submission1moviecatalog.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest{

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDataMovie()[0]
    private val dummyTv = DataDummy.generateDataTv()[0]
    private val movieId = dummyMovie.id
    private val tvId = dummyTv.id

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: GopoxMovieRepository

    @Mock
    private lateinit var dataObserver: Observer<MovieTvModel>


    @Before
    fun setUp(){
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun getDetailMovie(){
        val movie = MutableLiveData<MovieTvModel>()
        movie.value = dummyMovie

        `when` (movieId.let { repository.getDetailMovie(it) }).thenReturn(movie)
        val dataMovie = movieId.let { viewModel.getDetailMovie(it) }
        movieId.let { verify(repository).getDetailMovie(it) }

        assertNotNull(dataMovie)
        assertEquals(dummyMovie.id, dataMovie.value?.id)
        assertEquals(dummyMovie.title, dataMovie.value?.title)
        assertEquals(dummyMovie.backDropImage, dataMovie.value?.backDropImage)
        assertEquals(dummyMovie.posterImage, dataMovie.value?.posterImage)
        assertEquals(dummyMovie.duration, dataMovie.value?.duration)
        assertEquals(dummyMovie.year, dataMovie.value?.year)
        assertEquals(dummyMovie.star, dataMovie.value?.star)
        assertEquals(dummyMovie.language, dataMovie.value?.language)
        assertEquals(dummyMovie.tag, dataMovie.value?.tag)
        assertEquals(dummyMovie.synopsis, dataMovie.value?.synopsis)

        movieId.let { viewModel.getDetailMovie(it).observeForever(dataObserver) }
        verify(dataObserver).onChanged(dummyMovie)
    }

    @Test
    fun getDetailTv(){
        val tv = MutableLiveData<MovieTvModel>()
        tv.value = dummyTv

        `when` (tvId.let { repository.getDetailTv(it) }).thenReturn(tv)
        val dataTv = tvId.let { viewModel.getDetailTv(it) }
        tvId.let { verify(repository).getDetailTv(it) }

        assertNotNull(dataTv)
        assertEquals(dummyTv.id, dataTv.value?.id)
        assertEquals(dummyTv.title, dataTv.value?.title)
        assertEquals(dummyTv.backDropImage, dataTv.value?.backDropImage)
        assertEquals(dummyTv.posterImage, dataTv.value?.posterImage)
        assertEquals(dummyTv.duration, dataTv.value?.duration)
        assertEquals(dummyTv.year, dataTv.value?.year)
        assertEquals(dummyTv.star, dataTv.value?.star)
        assertEquals(dummyTv.language, dataTv.value?.language)
        assertEquals(dummyTv.tag, dataTv.value?.tag)
        assertEquals(dummyTv.synopsis, dataTv.value?.synopsis)

        tvId.let { viewModel.getDetailTv(it).observeForever(dataObserver) }
        verify(dataObserver).onChanged(dummyTv)
    }

}