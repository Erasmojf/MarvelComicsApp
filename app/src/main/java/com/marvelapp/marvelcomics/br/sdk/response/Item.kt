package com.marvelapp.br.sdk.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item(
    val name: String,
    val resourceURI: String,
    val type: String? = null
)