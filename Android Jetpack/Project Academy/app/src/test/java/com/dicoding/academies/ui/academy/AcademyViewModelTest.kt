package com.dicoding.academies.ui.academy

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AcademyViewModelTest{

    private lateinit var viewModel: AcademyViewModel

    @Before
    fun setUp(){
        viewModel = AcademyViewModel()
    }

    @Test
    fun getCourses(){
        val courseEntity = viewModel.getCourses()
        assertNotNull(courseEntity)
        assertEquals(5, courseEntity.size)
    }


}