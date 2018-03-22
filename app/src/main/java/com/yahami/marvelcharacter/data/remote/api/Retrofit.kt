package com.yahami.marvelcharacter.data.remote.api

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofit: Retrofit by lazy { createRetrofit() }

private fun createRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("http://gateway.marvel.com/v1/public/")
        .client(createHttpClient())
        .addConverters()
        .build()

fun createHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(prepareHeadersInterceptor())
        .addInterceptor(prepareLoggingInterceptor())
        .addInterceptor(prepareAddRequiredQueryInterceptor())
        .build()

private fun Retrofit.Builder.addConverters(): Retrofit.Builder = this
        .addConverterFactory(GsonConverterFactory.create(Gson()))  // convert data with Gson/JSON
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // allow RxJava2 types (Observable, Single)
