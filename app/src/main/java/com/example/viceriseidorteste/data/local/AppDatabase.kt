package com.example.viceriseidorteste.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.viceriseidorteste.data.local.daos.UserDAO
import com.example.viceriseidorteste.data.local.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDAO
}