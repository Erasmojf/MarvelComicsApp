package com.marvelapp.br.sdk

import com.marvelapp.br.sdk.response.PersonagensResponse
import com.marvelapp.br.sdk.response.comic.ComicDetalheResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    @GET("v1/public/characters?ts=1&apikey=e4d6796f47943cc5c67f740b6066edb2&hash=5a2dc91ac01f19f3be78b8b44b134bb0")
    fun getPersonagens(@Query("offset") offset: Int = 0) : Deferred<Response<PersonagensResponse>>

    @GET("v1/public/characters?ts=1&apikey=e4d6796f47943cc5c67f740b6066edb2&hash=5a2dc91ac01f19f3be78b8b44b134bb0")
    fun getPersonagem(@Path("characterId") characterId: Int) : Deferred<ComicDetalheResponse>
}