package ipvc.estg.olxcm.Adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.olxcm.EcraAnunc
import ipvc.estg.olxcm.ListaAnunciosFragment
import ipvc.estg.olxcm.R
import ipvc.estg.olxcm.api.Anuncio


class AnuncioAdapter(val anuncios: List<Anuncio>): RecyclerView.Adapter<AnunciosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnunciosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerlineanuncio, parent, false)
        return AnunciosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return anuncios.size
    }

    override fun onBindViewHolder(holder: AnunciosViewHolder, position: Int) {
        return holder.bind(anuncios[position])
    }

}

class AnunciosViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){



    private val titulo: TextView = itemView.findViewById(R.id.titulololo)
    private val preco:TextView = itemView.findViewById(R.id.descricaooooo)


    val bt4: ConstraintLayout = itemView.findViewById((R.id.lineline))



    fun bind(anuncio: Anuncio) {

        val context=titulo.context
        val tit= anuncio.titulo
        val preco2 =anuncio.preco
        val descricao=anuncio.descricao
        val localizacao = anuncio.localizacao
        val imagem = anuncio.imagem
        val id = anuncio.id
        val data=anuncio.data
        val user_id= anuncio.utilizador_id
        Log.d("AAAAIMMMAGEM", imagem)

        bt4.setOnClickListener {


            val intent = Intent( context, EcraAnunc::class.java).apply {
                putExtra("EXTRA_MESSAGE", tit )
                putExtra("PRECO", preco2 )
                putExtra("DESCRICAO", descricao )
                putExtra("LATITIDAO", localizacao )
                putExtra("ITUSAO", imagem )
                putExtra("IDU", id )
                putExtra("DATA", data )
                putExtra("USER", user_id )
            }
            context.startActivity(intent)


        }

        titulo.text = anuncio.titulo
        preco.text = anuncio.preco + "euros"


    }

}