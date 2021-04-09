package com.marvelapp.br.sdk.response.comic
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TextObject(
    val language: String,
    val text: String,
    val type: String
)