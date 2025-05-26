package com.example.viceriseidorteste.data.external

import okhttp3.Interceptor
import okhttp3.Response

class ExceptionHandlerInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (!response.isSuccessful) {
            val errorBody = response.body?.string() ?: "Unknown error"
            val code = response.code
            throw ApiException(code, "Error occurred", errorBody)
        }
        return response
    }
}