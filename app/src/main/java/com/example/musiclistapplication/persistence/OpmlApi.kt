package com.example.musiclistapplication.persistence

import com.example.musiclistapplication.model.Opml
import com.example.musiclistapplication.model.OpmlStation
import com.example.musiclistapplication.model.OpmlLink
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface OpmlApi {
    @GET("?render=json")
    fun getList(): Call<Opml?>

    @GET
    fun getListByCategory(@Url url: String?, @Query( "render" ) render: String?): Call<OpmlLink?>


    @GET
    fun getListByAudioItems(@Url url: String?, @Query( "render" ) render: String?): Call<OpmlStation?>
}