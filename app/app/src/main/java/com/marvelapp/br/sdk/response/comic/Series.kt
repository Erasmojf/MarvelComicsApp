package com.marvelapp.br.sdk.response.comic
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Series(
    val name: String,
    val resourceURI: String
)