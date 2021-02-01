package com.example.mymuseum.InfoApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymuseum.Museu.Decoration
import com.example.mymuseum.Museu.MuseuRecyclerAdapter
import com.example.mymuseum.Museu.MuseuSource
import com.example.mymuseum.R
import kotlinx.android.synthetic.main.fragment_museu_fragment.*


class MuseuFragemnt : Fragment() {


    var lista = MuseuSource.createDataSet()
    lateinit var museuAdapter : MuseuInfoRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_museu_fragment, container, false)

        //initRecyclerView()

        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()

    }

    fun initRecyclerView(){
        recyclerViewMuseuInfo.apply {
            layoutManager = LinearLayoutManager(this.context)
            val decoration = Decoration(30)
            addItemDecoration(decoration)
            museuAdapter = MuseuInfoRecyclerAdapter(lista)
            adapter = museuAdapter

        }

    }





}