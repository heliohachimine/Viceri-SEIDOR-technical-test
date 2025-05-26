package com.example.viceriseidorteste.data.external.dtos

data class AddressDto(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoDto
)

data class GeoDto(
    val lat: String,
    val lng: String
)
