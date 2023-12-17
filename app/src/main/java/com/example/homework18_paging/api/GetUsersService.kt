package com.example.homework18_paging.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetUsersService {
    @GET("users")
    suspend fun getUsers(@Query("page") page: Int, @Query("per_page") perPage: Int): Response<GetUsersResponse>
}