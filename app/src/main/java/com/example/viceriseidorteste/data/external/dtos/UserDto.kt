package com.example.viceriseidorteste.data.external.dtos

data class UserDto (
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressDto,
    val phone: String,
    val website: String,
    val company: CompanyDto
    )