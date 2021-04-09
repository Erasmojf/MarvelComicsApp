package com.marvelapp.br.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.marvelapp.br.config.initPagingConfig
import com.marvelapp.br.sdk.API
import com.marvelapp.br.sdk.paging.PersonagemDataSource
import com.marvelapp.br.sdk.repository.PersonagemRepository
import com.marvelapp.br.sdk.response.Result
import com.marvelapp.br.sdk.response.comic.ComicDetalheResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class PersonagemViewModel(private val api: API, private val repo: PersonagemRepository) :  ViewModel() {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    private var _personagemComicDetalhe: MutableLiveData<ComicDetalheResponse> = MutableLiveData()
    var personagensLiveData: LiveData<PagedList<Result>>
    val personagemDetalheLiveData: LiveData<ComicDetalheResponse> = _personagemComicDetalhe

    init {
        personagensLiveData = initPagedListBuilder(initPagingConfig()).build()
    }

    fun getPersonagens(): LiveData<PagedList<Result>> = personagensLiveData

    fun getComics(id: Int) {
        scope.launch {
            try {
                val repos = repo.getComic(id).await()
                _personagemComicDetalhe.postValue(repos)
            } catch (t: Throwable) {
                Log.d("PersonagemViewModel", "Error")
            }
        }
    }

    private fun initPagedListBuilder(config: PagedList.Config) : LivePagedListBuilder<Int, Result> {
        val dataSourceFactory = object : DataSource.Factory<Int,Result>(){
            override fun create(): DataSource<Int, Result> {
                return PersonagemDataSource(api)
            }
        }
        return LivePagedListBuilder<Int, Result>(dataSourceFactory,config)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}


