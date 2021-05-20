package com.mkhairulramadhan.submission1moviecatalog.utils

import androidx.paging.PagedList
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

object PagedListTesting {

    fun <T> testPagedList(list: List<T>): PagedList<*> {
        val paged = mock(PagedList::class.java) as PagedList<*>
        `when`(paged[ArgumentMatchers.anyInt()]).then{invoc ->
            val i = invoc.arguments.first() as Int
            list[i]
        }
        `when`(paged.size).thenReturn(list.size)
        return paged
    }

}