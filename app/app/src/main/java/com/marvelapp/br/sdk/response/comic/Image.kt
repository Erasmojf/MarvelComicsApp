package com.marvelapp.br.sdk.response.comic
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    val extension: String,
    val path: String
)