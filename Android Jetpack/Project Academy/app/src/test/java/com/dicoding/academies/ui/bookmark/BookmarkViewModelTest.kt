package com.dicoding.academies.ui.bookmark

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class BookmarkViewModelTest{

    private lateinit var viewModel: BookmarkViewModel

    @Before
    fun setUp(){
        viewModel = BookmarkViewModel()
    }

    @Test
    fun getBookmarks(){
        val courseEntity = viewModel.getBookmarks()
        assertNotNull(courseEntity)
        assertEquals(5, courseEntity.size)
    }

}