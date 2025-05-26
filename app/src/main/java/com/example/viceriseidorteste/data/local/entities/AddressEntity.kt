package com.example.viceriseidorteste.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity

@Entity(tableName = "addresses")
data class AddressEntity(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    @Embedded(prefix = "geo_")
    val geo: GeoEntity
)

@Entity(tableName = "geos")
data class GeoEntity(
    val lat: String,
    val lng: String
)
