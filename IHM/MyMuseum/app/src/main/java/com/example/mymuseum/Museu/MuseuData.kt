package com.example.mymuseum.Museu

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import com.example.mymuseum.Item.ItemData
import java.io.Serializable

data class MuseuData(
    var nome: String?,

    var imagem: String?,

    var itens: ArrayList<ItemData>,

    var uri : String,

    var horario : String,

    var website : String,

    var descricao : String

) : Serializable {


}