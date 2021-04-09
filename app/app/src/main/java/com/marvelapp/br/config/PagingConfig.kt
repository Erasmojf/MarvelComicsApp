package com.marvelapp.br.config

import androidx.paging.PagedList

internal fun initPagingConfig(
    pageSize: Int = 10,
    enablePlaceHolder: Boolean = true,
    prefetchDistance: Int = 2
) =
    PagedList.Config.Builder()
        .setPageSize(pageSize)
        .setEnablePlaceholders(enablePlaceHolder)
        .setPrefetchDistance(prefetchDistance)
        .build()
