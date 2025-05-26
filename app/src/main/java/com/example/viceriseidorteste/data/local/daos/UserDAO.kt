package com.example.viceriseidorteste.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.viceriseidorteste.data.local.entities.UserEntity

@Dao
interface UserDAO {
     @Query("SELECT * FROM users")
     suspend fun getAllUsers(): List<UserEntity>

     @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
     suspend fun insertUsers(users: List<UserEntity>)

     @Query("DELETE FROM users")
     suspend fun deleteAllUsers()
}