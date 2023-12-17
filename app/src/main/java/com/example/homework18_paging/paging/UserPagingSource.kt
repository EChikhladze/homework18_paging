package com.example.homework18_paging.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.homework18_paging.common.User
import com.example.homework18_paging.api.GetUsersService
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_KEY = 1

class UserPagingSource(private val apiService: GetUsersService) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {

        return try {
            val position = params.key ?: STARTING_KEY
            val response = apiService.getUsers(position, params.loadSize)
            if (response.isSuccessful) {
                LoadResult.Page(
                    data = response.body()!!.data,
                    prevKey = if (position == STARTING_KEY) null else position - 1,
                    nextKey = if (position == response.body()!!.totalPages) null else position + 1
                )
            } else {
                LoadResult.Error(Exception("No Response"))
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}