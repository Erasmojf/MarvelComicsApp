package com.marvelapp.br.sdk.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)