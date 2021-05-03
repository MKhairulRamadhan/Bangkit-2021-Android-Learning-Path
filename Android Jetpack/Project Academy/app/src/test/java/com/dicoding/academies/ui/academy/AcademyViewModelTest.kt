package com.dicoding.academies.ui.academy

import com.dicoding.academies.data.CourseEntity
import com.dicoding.academies.data.source.AcademyRepository
import com.dicoding.academies.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AcademyViewModelTest{

    private lateinit var viewModel: AcademyViewModel

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Before
    fun setUp(){
        viewModel = AcademyViewModel(academyRepository)
    }

    @Test
    fun getCourses(){
        `when` (academyRepository.getAllCourses()).thenReturn(DataDummy.generateDummyCourses() as ArrayList<CourseEntity>)
        val courseEntity = viewModel.getCourses()
        verify(academyRepository).getAllCourses()
        assertNotNull(courseEntity)
        assertEquals(5, courseEntity.size)
    }


}