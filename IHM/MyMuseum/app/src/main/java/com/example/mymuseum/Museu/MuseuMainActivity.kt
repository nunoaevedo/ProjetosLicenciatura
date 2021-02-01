package com.example.mymuseum.Museu


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.bumptech.glide.Glide
import com.example.mymuseum.InfoApp.InfoActivity
import com.example.mymuseum.Item.ItemMainActivity
import com.example.mymuseum.LogIn.ActivityLogIn
import com.example.mymuseum.R
import com.example.mymuseum.databinding.ActivityMuseuMainBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_museu_main.*


class MuseuMainActivity : AppCompatActivity(), MuseuRecyclerAdapter.OnItemClickListener {


    private lateinit var museuAdapter : MuseuRecyclerAdapter
    private var lista = MuseuSource.createDataSet()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museu_main)

        supportActionBar?.title = "Museus"




        sair.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val logInIntent = Intent(this, ActivityLogIn::class.java)
            startActivity(logInIntent)
            finish()
        }



        initRecyclerView()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item!!.itemId){
            R.id.informacoes ->{
                val infoIntent = Intent(this, InfoActivity::class.java)
                startActivity(infoIntent)
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initRecyclerView(){
        recyclerViewMuseu.apply {
            layoutManager = LinearLayoutManager(this@MuseuMainActivity)
            val decoration = Decoration(30)
            addItemDecoration(decoration)
            museuAdapter = MuseuRecyclerAdapter(lista, this@MuseuMainActivity)
            adapter = museuAdapter

        }

    }


    override fun OnItemClick(position: Int) {
        val clickedItem = lista[position]



        val dialog = MaterialDialog(this).customView(R.layout.museu_dialog)
        dialog.setTitle("Deseja visitar este museu?")

        dialog.findViewById<TextView>(R.id.descricao_museu_dialog).text = clickedItem.descricao
        dialog.findViewById<TextView>(R.id.nome_museu_dialog).text = clickedItem.nome
        Glide.with(this).load(clickedItem.imagem).into(dialog.findViewById(R.id.imagem_museu_dialog))

        dialog.findViewById<TextView>(R.id.positive_button).setOnClickListener {
            val itemIntent = Intent(this, ItemMainActivity::class.java)
            itemIntent.putExtra("museu", clickedItem)
            startActivity(itemIntent)
            dialog.dismiss()

        }
        dialog.findViewById<TextView>(R.id.negative_button).setOnClickListener{

            dialog.dismiss()

        }

        dialog.show()

    }


}