package com.marvelapp.br.sdk.response.comic
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Price(
    val price: Double,
    val type: String
)