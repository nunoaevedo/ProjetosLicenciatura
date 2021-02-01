package com.example.mymuseum.Item

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.bumptech.glide.Glide
import com.example.mymuseum.Museu.Decoration
import com.example.mymuseum.Museu.MuseuData
import com.example.mymuseum.Museu.MuseuRecyclerAdapter
import com.example.mymuseum.Museu.MuseuSource
import com.example.mymuseum.R
import kotlinx.android.synthetic.main.activity_item_main.*
import kotlinx.android.synthetic.main.activity_museu_main.*
import java.io.Serializable

class ItemMainActivity : AppCompatActivity() {

    private lateinit var itemAdapter : ItemRecyclerAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_main)

        var museu : MuseuData = intent.getSerializableExtra("museu") as MuseuData

        var lista = museu.itens

        supportActionBar?.setTitle(museu.nome)

        mapabttn.setOnClickListener{
            val uriMap = Uri.parse(museu.uri)
            val intent = Intent(Intent.ACTION_VIEW, uriMap)
            intent.setPackage("com.google.android.apps.maps")
            if(intent.resolveActivity(packageManager)!=null){
                startActivity(intent)
            }

        }

        infobttn.setOnClickListener{
            infoDialog(museu)
        }

        voltar_bttn.setOnClickListener{
            onBackPressed()
        }

        partilharbttn.setOnClickListener {
            partilharDialog(museu)
        }


        initRecyclerView(lista)

    }

    private fun initRecyclerView(lista : ArrayList<ItemData>){
        recyclerViewItem.apply {
            layoutManager = LinearLayoutManager(this@ItemMainActivity)
            val decoration = Decoration(30)
            addItemDecoration(decoration)
            itemAdapter = ItemRecyclerAdapter(lista)
            adapter = itemAdapter

        }
    }

    //DIALOGO INFORMACAO
    private fun infoDialog(museu : MuseuData){
        val dialog = MaterialDialog(this).noAutoDismiss().customView(R.layout.item_museu_info_dialog)

        dialog.findViewById<TextView>(R.id.horario_info).text = museu.horario
        dialog.findViewById<TextView>(R.id.website_info).text = museu.website

        dialog.findViewById<TextView>(R.id.positive_button).setOnClickListener {

            val intent  =  Intent(Intent.ACTION_VIEW, Uri.parse(museu.website))
            startActivity(intent)
            dialog.dismiss()

        }
        dialog.findViewById<TextView>(R.id.negative_button).setOnClickListener{

            dialog.dismiss()

        }

        dialog.show()
    }


    //DIALOGO PARTILHAR
    private fun partilharDialog( museu: MuseuData)
    {
        val dialog = MaterialDialog(this).noAutoDismiss().customView(R.layout.partilhar_dialog)



        dialog.findViewById<ImageView>(R.id.facebookImage).setOnClickListener {

            if(checkFacebook(this, "com.facebook.katana")){
                val intent = Intent(Intent.ACTION_SEND)
                intent.type ="text/plain"

                val uriString : String = "Hoje visitei o museu ${museu.nome} através da aplicação MyMuseum!"

                intent.putExtra(Intent.EXTRA_TEXT, uriString)
                intent.`package` = "com.facebook.katana"
                startActivity(intent)

                dialog.dismiss()

            }else{
                Toast.makeText(this, "Não tem o facebook instalado.", Toast.LENGTH_SHORT).show()
            }

        }
        dialog.findViewById<ImageView>(R.id.twitterImage).setOnClickListener{

            try{
                val intent = Intent(Intent.ACTION_SEND)
                intent.type ="text/plain"

                val uriString : String = "Hoje visitei o museu ${museu.nome} através da aplicação MyMuseum!"

                intent.putExtra(Intent.EXTRA_TEXT, uriString)
                intent.`package` = "com.twitter.android"
                startActivity(intent)

                dialog.dismiss()

            }
            catch(exp : Exception){
            Toast.makeText(this, "Não tem o twitter instalado.", Toast.LENGTH_SHORT).show()
            }

        }

        dialog.show()
    }


    //VER SE TEM O FACEBOOK INSTALADO
    private fun checkFacebook(context: Context, target: String ) : Boolean{

        val pm = context.packageManager

        try{
            pm.getPackageInfo(target, PackageManager.GET_META_DATA)
        }catch (exp : PackageManager.NameNotFoundException){
            return false
        }

        return true

    }




}