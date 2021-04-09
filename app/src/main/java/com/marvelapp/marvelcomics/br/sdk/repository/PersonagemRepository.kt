package com.marvelapp.br.sdk.repository

import com.marvelapp.br.sdk.API
import com.marvelapp.br.sdk.response.comic.ComicDetalheResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Response

class PersonagemRepository(private val api: API) {

    suspend fun
            getComic (id: Int): Deferred<ComicDetalheResponse> {
       return withContext(IO) {
            async { api.getPersonagem(id).await() }
        }
    }
}