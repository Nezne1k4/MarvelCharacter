package com.yahami.marvelcharacter.data.remote.api

import com.yahami.marvelcharacter.BuildConfig
import com.yahami.marvelcharacter.utils.calculatedMd5
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Header Interceptor
 */
fun prepareHeadersInterceptor() = Interceptor { chain ->
    chain.proceed(chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Accept-Language", "en")
            .addHeader("Content-Type", "application/json")
            .build())
}

/**
 * Logging Interceptor
 */
fun prepareLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
    else HttpLoggingInterceptor.Level.NONE
}

/**
 * Auth parameter following Marvel api
 */
fun prepareAddRequiredQueryInterceptor() = Interceptor { chain ->
    val originalRequest = chain.request()

    val timeStamp = System.currentTimeMillis()

    // Url customization: add query parameters
    val url = originalRequest.url().newBuilder()
            .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
            .addQueryParameter("hash", calculatedMd5(timeStamp.toString() + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY))
            .addQueryParameter("ts", "$timeStamp")
            .build()

    // Request customization: set custom url
    val request = originalRequest
            .newBuilder()
            .url(url)
            .build()
    chain.proceed(request)
}