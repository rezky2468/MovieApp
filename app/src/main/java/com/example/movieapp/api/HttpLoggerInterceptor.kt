package com.example.movieapp.api

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

class HttpLoggerInterceptor : Interceptor {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // You can choose other logging levels as needed
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        logRequest(request)

        val response = chain.proceed(request)
        logResponse(response)

        return response
    }

    private fun logRequest(request: okhttp3.Request) {
        // Log your request information here (e.g., request.method, request.url, request.headers, etc.)
        println("Request: ${request.method} ${request.url}")
        println("Headers: ${request.headers}")
    }

    private fun logResponse(response: Response) {
        // Log your response information here (e.g., response.code, response.message, response.headers, etc.)
        println("Response: ${response.code} ${response.message}")
        println("Headers: ${response.headers}")
        println("Body: ${response.body?.string()}")
    }

}