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
class MovieTvViewModelTest{

    private lateinit var viewModel: MovieTvViewModel

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: GopoxMovieRepository

    @Mock
    private lateinit var dataObserver: Observer<List<MovieTvModel>>

    @Before
    fun setUp(){
        viewModel = MovieTvViewModel(repository)
    }

    @Test
    fun getMovieData(){
        val dataMovie = DataDummy.generateDataMovie()
        val movie = MutableLiveData<List<MovieTvModel>>()
        movie.value = dataMovie

        `when` (repository.getAllMovie()).thenReturn(movie)
        val movieList = viewModel.getMovieData().value
        verify(repository).getAllMovie()

        assertNotNull(movieList)
        assertEquals(10, movieList?.size)

        viewModel.getMovieData().observeForever(dataObserver)
        verify(dataObserver).onChanged(dataMovie)
    }

    @Test
    fun getTvData(){
        val dataTv = DataDummy.generateDataTv()
        val tv = MutableLiveData<List<MovieTvModel>>()
        tv.value = dataTv

        `when` (repository.getAllTv()).thenReturn(tv)
        val tvList = viewModel.getTvData().value
        verify(repository).getAllTv()

        assertNotNull(tvList)
        assertEquals(10, tvList?.size)

        viewModel.getTvData().observeForever(dataObserver)
        verify(dataObserver).onChanged(dataTv)
    }

}