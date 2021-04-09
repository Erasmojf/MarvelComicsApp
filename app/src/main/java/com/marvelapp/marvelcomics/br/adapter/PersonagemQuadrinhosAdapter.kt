package com.marvelapp.br.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.marvelapp.br.R
import com.marvelapp.br.sdk.response.comic.Result
import com.squareup.picasso.Picasso

class PersonagemQuadrinhosAdapter(
    private val list: List<Result>,
    private val openURL: (url: String) -> Unit
) : RecyclerView.Adapter<PersonagemQuadrinhosAdapter.VH>(), Filterable {

    var filteredList: List<Result>
        init {
            filteredList = list
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VH {
        return VH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.personagem_comic_list_item,
                parent,
                false
            ),
            openURL
        )
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(filteredList[position])
    }

    class VH(
        itemView: View,
        private val openURL: (url: String) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val comic_image = itemView.findViewById<ImageView>(R.id.comic_item_img)
        private val comic_name = itemView.findViewById<TextView>(R.id.comic_item_title)
        private val comic_price = itemView.findViewById<TextView>(R.id.comic_item_price)
        private val comic_description = itemView.findViewById<TextView>(R.id.comic_item_description)
        private val comic_buy = itemView.findViewById<MaterialButton>(R.id.comic_item_buy)
        private val comic_details = itemView.findViewById<MaterialButton>(R.id.comic_item_detail)

        fun bind(item: Result) {
            Picasso.get().load("${item.thumbnail.path}.${item.thumbnail.extension}").fit()
                .centerCrop().into(comic_image)
            comic_name.text = item.title
            comic_description.text = item.description
            comic_price.text = if(item.prices.size > 1) "U$ ${item.prices[1].price}" else ""
            comic_buy.setOnClickListener {
                openURL(item.urls[1].url)
            }
            comic_details.setOnClickListener {
                openURL(item.urls[0].url)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if(constraint.toString().isNotEmpty()) {
                  filteredList = list.filter {  it.title.toUpperCase().contains(constraint.toString().toUpperCase())}
                } else {
                    filteredList = list
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as List<Result>
                notifyDataSetChanged()
            }
        }
    }
}