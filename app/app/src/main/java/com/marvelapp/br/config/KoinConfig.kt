package com.marvelapp.br.config

import com.marvelapp.br.sdk.API
import com.marvelapp.br.sdk.repository.PersonagemRepository
import com.marvelapp.br.viewmodel.PersonagemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { PersonagemViewModel(get(),get()) }
}

val repoModule = module {
   single { PersonagemRepository(get()) }
}

val remoteModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { createApi<API>(get()) }
}
