package com.marvelapp.br.sdk.response.comic

import com.marvelapp.br.sdk.response.Item
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)