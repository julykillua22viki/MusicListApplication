package com.example.musiclistapplication.model

import com.squareup.moshi.Json
data class Browse(
    val element: String?,
    val type: String?,
    val text: String?,
    val URL: String?,
    val key: String? )