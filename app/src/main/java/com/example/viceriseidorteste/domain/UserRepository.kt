package com.example.viceriseidorteste.domain

import com.example.viceriseidorteste.data.external.ApiService
import com.example.viceriseidorteste.data.external.dtos.UserDto
import com.example.viceriseidorteste.data.local.daos.UserDAO
import com.example.viceriseidorteste.data.local.entities.UserEntity
import com.example.viceriseidorteste.domain.models.User
import kotlinx.coroutines.delay
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDAO
) {
    suspend fun getUsers(page: Int, pageSize: Int): List<User> {
        delay(1000)
        try {
            val userEntities: List<UserDto> = apiService.getUsers()
            return userEntities.map { userDto ->
                User.fromUserDto(userDto)
            }
        } catch (e: Exception) {
            val users = userDao.getAllUsers()
            return users.map { userEntity ->
                User.fromUserEntity(userEntity)
            }
        }
    }
}