package com.marvelapp.br.config

import androidx.recyclerview.widget.DiffUtil
import com.marvelapp.br.sdk.response.Result

class DiffUtilCallBack: DiffUtil.ItemCallback<com.marvelapp.br.sdk.response.Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

}