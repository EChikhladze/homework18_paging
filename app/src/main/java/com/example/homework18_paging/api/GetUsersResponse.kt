package com.example.homework18_paging.api

import com.example.homework18_paging.common.User
import com.squareup.moshi.Json

data class GetUsersResponse(
    @Json(name = "page") val page: Int,
    @Json(name = "per_page") val perPage: Int,
    @Json(name = "total") val totalUsers: Int,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "data") val data: List<User>
)