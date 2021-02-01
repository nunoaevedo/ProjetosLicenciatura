package com.example.mymuseum.Museu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mymuseum.R
import kotlinx.android.synthetic.main.museu_adapter.view.*

class MuseuRecyclerAdapter(var items : List<MuseuData>, private val listener : OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MuseuViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.museu_adapter, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){

            is MuseuViewHolder ->{
                holder.bind(items.get(position))
            }

        }
    }




    inner class MuseuViewHolder( itemView: View ) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        val museuImage = itemView.imagem_museu
        val museuNome = itemView.nome_museu

        fun bind(museuData: MuseuData){
            museuNome.setText(museuData.nome)

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(museuData.imagem)
                .into(museuImage)

        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.OnItemClick(position)
            }

        }
    }

    interface OnItemClickListener{
        fun OnItemClick(position: Int)
    }






}

