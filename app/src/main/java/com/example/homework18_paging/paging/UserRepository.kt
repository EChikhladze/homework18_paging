package com.example.homework18_paging.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.homework18_paging.common.User
import com.example.homework18_paging.api.GetUsersService
import kotlinx.coroutines.flow.Flow

class UserRepository(private val service: GetUsersService) {

    fun getUsersFlow(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = USERS_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { UserPagingSource(service) }
        ).flow
    }

    companion object {
        const val USERS_PAGE_SIZE = 6
    }
}