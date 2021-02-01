package com.example.mymuseum.InfoApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymuseum.Museu.MuseuData
import com.example.mymuseum.R
import kotlinx.android.synthetic.main.museu_fragment_adapter.view.*

class MuseuInfoRecyclerAdapter(var items : List<MuseuData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MuseuInfoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.museu_fragment_adapter, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){

            is MuseuInfoViewHolder ->{
                holder.bind(items[position])
            }

        }
    }




    class MuseuInfoViewHolder constructor( itemView: View ) : RecyclerView.ViewHolder(itemView){

        val museuNome = itemView.titulo_museu
        val museuHor = itemView.horario_temp_museu
        val museuWeb = itemView.website_temp_info
        val descMuseu = itemView.descricao_temp_info


        fun bind(museuData: MuseuData){
            museuNome.setText(museuData.nome)
            museuHor.setText(museuData.horario)
            museuWeb.setText(museuData.website)
            descMuseu.setText(museuData.descricao)

        }

    }








}

