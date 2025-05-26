package com.example.viceriseidorteste.data.local.entities

import androidx.room.Entity

@Entity(tableName = "companies")
data class CompanyEntity(
    val name: String,
    val catchPhrase: String,
    val bs: String
)
