package com.example.viceriseidorteste.data.external

import java.io.IOException

class ApiException(
    val code: Int,
    override val message: String,
    val body: String? = null
) : IOException()