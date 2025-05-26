package com.example.viceriseidorteste.domain.models

import com.example.viceriseidorteste.data.external.dtos.UserDto
import com.example.viceriseidorteste.data.local.entities.UserEntity

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
) {
    fun toUserEntity(): UserEntity {
        return UserEntity(
            id,
            name,
            username,
            email,
            address.toAddressEntity(),
            phone,
            website,
            company.toCompanyEntity()
        )
    }

    companion object {
        fun fromUserDto(userDto: UserDto): User {
            return User(
                userDto.id,
                userDto.name,
                userDto.username,
                userDto.email,
                Address.fromAddressDto(userDto.address),
                userDto.phone,
                userDto.website,
                Company.fromCompanyDto(userDto.company)
            )
        }
        fun fromUserEntity(userEntity: UserEntity): User {
            return User(
                userEntity.id,
                userEntity.name,
                userEntity.username,
                userEntity.email,
                Address.fromAddressEntity(userEntity.address),
                userEntity.phone,
                userEntity.website,
                Company.fromCompanyEntity(userEntity.company)
            )
        }
    }
}
