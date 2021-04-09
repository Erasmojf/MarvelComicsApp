package com.marvelapp.br.sdk.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonagensResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)