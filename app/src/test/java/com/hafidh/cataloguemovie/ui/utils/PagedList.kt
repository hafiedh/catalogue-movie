package com.hafidh.cataloguemovie.ui.utils

import androidx.paging.PagedList
import org.mockito.Mockito.*

@Suppress("UNCHECKED_CAST")
object PagedList {
    fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val mock = mock(PagedList::class.java) as PagedList<T>
        `when`(mock[anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        `when`(mock.size).thenReturn(list.size)

        return mock
    }
}