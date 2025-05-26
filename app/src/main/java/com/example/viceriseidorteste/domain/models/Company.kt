package com.example.viceriseidorteste.domain.models

import com.example.viceriseidorteste.data.external.dtos.CompanyDto
import com.example.viceriseidorteste.data.local.entities.CompanyEntity

data class Company(
    val name: String,
    val catchPhrase: String,
    val bs: String
) {
    fun toCompanyEntity(): CompanyEntity {
        return CompanyEntity(name, catchPhrase, bs)
    }

    companion object {
        fun fromCompanyDto(companyDto: CompanyDto): Company {
            return Company(companyDto.name, companyDto.catchPhrase, companyDto.bs)
        }
        fun fromCompanyEntity(companyEntity: CompanyEntity): Company {
            return Company(companyEntity.name, companyEntity.catchPhrase, companyEntity.bs)
        }
    }
}
