package com.marvelapp.br.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.marvelapp.br.R
import com.marvelapp.br.config.DiffUtilCallBack
import com.marvelapp.br.sdk.response.Result
import com.squareup.picasso.Picasso

class PersonagemPagedAdapter(private val onClickItem: (Int)-> Unit, private val onLoadComplete: ()-> Unit): PagedListAdapter<Result, PersonagemPagedAdapter.VH>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.personagem_list_item,parent,false), onClickItem, onLoadComplete)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class VH(itemView: View,private val  listener:(Int) -> Unit, private val onLoadComplete: ()-> Unit) : RecyclerView.ViewHolder(itemView) {
        private val card = itemView.findViewById<MaterialCardView>(R.id.card)
        private val img = itemView.findViewById<ImageView>(R.id.iv_personagem_img)
        private val name = itemView.findViewById<AppCompatTextView>(R.id.tv_personagem_nome)

        fun bind(item: Result) {
            name.text = item.name
            Picasso.get().load("${item.thumbnail.path}.${item.thumbnail.extension}").fit().centerCrop().into(img)
            card.setOnClickListener { listener(item.id) }
            onLoadComplete()
        }
    }
}