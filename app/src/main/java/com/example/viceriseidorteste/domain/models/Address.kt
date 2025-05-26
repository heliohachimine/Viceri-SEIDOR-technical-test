package com.example.viceriseidorteste.domain.models

import com.example.viceriseidorteste.data.external.dtos.AddressDto
import com.example.viceriseidorteste.data.local.entities.AddressEntity

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo
) {
    fun toAddressEntity(): AddressEntity {
        return AddressEntity(
            street = street,
            suite = suite,
            city = city,
            zipcode = zipcode,
            geo = geo.toGeoEntity()
        )
    }

    companion object {
        fun fromAddressDto(addressDto: AddressDto): Address {
            return Address(
                street = addressDto.street,
                suite = addressDto.suite,
                city = addressDto.city,
                zipcode = addressDto.zipcode,
                geo = Geo.fromGeoDto(addressDto.geo)
            )
        }

        fun fromAddressEntity(addressEntity: AddressEntity): Address {
            return Address(
                street = addressEntity.street,
                suite = addressEntity.suite,
                city = addressEntity.city,
                zipcode = addressEntity.zipcode,
                geo = Geo.fromGeoEntity(addressEntity.geo)
            )
        }
    }
}
