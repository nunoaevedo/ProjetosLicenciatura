package com.example.mymuseum.Item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mymuseum.R
import kotlinx.android.synthetic.main.item_adapter.view.*


class ItemRecyclerAdapter(var items : List<ItemData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_adapter, parent, false)
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ItemRecyclerAdapter.ItemViewHolder ->{
            holder.bind(items.get(position))
        }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }



    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val itemImage = itemView.imagem_item
        val itemNome = itemView.nome_item

        fun bind(itemData: ItemData){
            itemNome.setText(itemData.nome)

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(itemData.imagem)
                .into(itemImage)

        }




    }



}