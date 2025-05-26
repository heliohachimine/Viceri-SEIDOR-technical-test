package com.example.viceriseidorteste.data.external

import com.example.viceriseidorteste.data.external.dtos.UserDto
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<UserDto>
}