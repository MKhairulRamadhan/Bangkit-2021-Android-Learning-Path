package com.mkhairulramadhan.submission1moviecatalog.viewModel

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieTvViewModelTest{

    private lateinit var viewModel: MovieTvViewModel

    @Before
    fun setUp(){
        viewModel = MovieTvViewModel()
    }

    @Test
    fun getMovieData(){
        val dataMovie = viewModel.getMovieData()
        assertNotNull(dataMovie)
        assertEquals(14, dataMovie.size)
    }

    @Test
    fun getTvData(){
        val dataTv = viewModel.getTvData()
        assertNotNull(dataTv)
        assertEquals(15, dataTv.size)
    }

}