package com.marvelapp.br.sdk.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.marvelapp.br.sdk.API
import com.marvelapp.br.sdk.response.Result
import kotlinx.coroutines.*

class PersonagemDataSource (
    private val api: API
) : PageKeyedDataSource<Int,Result>() {


    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int,Result>
    ) {
        request(0, callback, null)
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Result>
    ) {
        request(params.key + 1, null, callback)
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Result>
    ) {
        request(params.key -1 , null, callback)
    }

    private fun request(
        pageKey: Int,
        initialCallback: LoadInitialCallback<Int, Result>?,
        callback: LoadCallback<Int, Result>?
    ) {
        scope.launch {
            try {
                val response = api.getPersonagens(offset = pageKey * 10).await()
                when {
                    response.isSuccessful -> {
                        val listing = response.body()
                        val personagens = listing?.data?.results?.map { it }
                        val list = personagens ?: listOf()

                        initialCallback?.onResult(list,null,pageKey)
                        callback?.onResult(list, pageKey)
                    }
                    else -> {
                        Log.e("PersonagemDataSource", "Deu ruinzao")
                    }
                }
            } catch (e: Exception) {
                Log.e("PersonagemDataSource", "Deu ruinzao")
            }
        }
    }
}