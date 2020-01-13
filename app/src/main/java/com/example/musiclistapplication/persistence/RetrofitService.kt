package com.example.musiclistapplication.persistence

import com.example.musiclistapplication.model.Browse
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

public object RetrofitService {

    var logging   = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    var client : OkHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()

    val moshi = Moshi.Builder().build()

    private var retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://opml.radiotime.com/")
        .addConverterFactory(MoshiConverterFactory.create( moshi ))

        .client(client)
        .build()

    public fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

}