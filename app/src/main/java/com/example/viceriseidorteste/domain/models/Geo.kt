package com.example.viceriseidorteste.domain.models

import com.example.viceriseidorteste.data.external.dtos.GeoDto
import com.example.viceriseidorteste.data.local.entities.GeoEntity

data class Geo(
    val lat: String,
    val lng: String
) {
    fun toGeoEntity(): GeoEntity {
        return GeoEntity(lat, lng)
    }

    companion object {
        fun fromGeoDto(geoDto: GeoDto): Geo {
            return Geo(geoDto.lat, geoDto.lng)
        }
        fun fromGeoEntity(geoEntity: GeoEntity): Geo {
            return Geo(geoEntity.lat, geoEntity.lng)
        }
    }
}
