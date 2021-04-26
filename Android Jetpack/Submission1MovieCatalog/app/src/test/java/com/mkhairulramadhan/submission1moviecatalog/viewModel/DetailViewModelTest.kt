package com.mkhairulramadhan.submission1moviecatalog.viewModel

import com.mkhairulramadhan.submission1moviecatalog.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailViewModelTest{

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDataMovie()[0]
    private val dummyTv = DataDummy.generateDataTv()[0]
    private val movieId = dummyMovie.id
    private val tvId = dummyTv.id

    @Before
    fun setUp(){
        viewModel = DetailViewModel()
        viewModel.setMovieId(movieId)
        viewModel.setTvId(tvId)
    }

    @Test
    fun getDetailMovie(){
        viewModel.setMovieId(dummyMovie.id)
        val dataMovie = viewModel.getDetailMovie()
        assertNotNull(dataMovie)
        assertEquals(dummyMovie.id, dataMovie.id)
        assertEquals(dummyMovie.title, dataMovie.title)
        assertEquals(dummyMovie.image, dataMovie.image)
        assertEquals(dummyMovie.duration, dataMovie.duration)
        assertEquals(dummyMovie.age, dataMovie.age)
        assertEquals(dummyMovie.year, dataMovie.year)
        assertEquals(dummyMovie.star, dataMovie.star)
        assertEquals(dummyMovie.director, dataMovie.director)
        assertEquals(dummyMovie.tag, dataMovie.tag)
        assertEquals(dummyMovie.synopsis, dataMovie.synopsis)
    }

    @Test
    fun getDetailTv(){
        viewModel.setTvId(dummyTv.id)
        val dataTv = viewModel.getDetailTv()
        assertNotNull(dataTv)
        assertEquals(dummyTv.id, dataTv.id)
        assertEquals(dummyTv.title, dataTv.title)
        assertEquals(dummyTv.image, dataTv.image)
        assertEquals(dummyTv.duration, dataTv.duration)
        assertEquals(dummyTv.age, dataTv.age)
        assertEquals(dummyTv.year, dataTv.year)
        assertEquals(dummyTv.star, dataTv.star)
        assertEquals(dummyTv.director, dataTv.director)
        assertEquals(dummyTv.tag, dataTv.tag)
        assertEquals(dummyTv.synopsis, dataTv.synopsis)
    }

}