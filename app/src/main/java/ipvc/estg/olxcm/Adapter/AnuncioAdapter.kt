package ipvc.estg.olxcm.Adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
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

       /* bt4.setOnClickListener {
            val context=titulo.context
            val tit= titulo.text.toString()
            val descricao= descricao.text.toString()
            val laitude = ocorrencia.latitude
            val longitude = ocorrencia.longitude
            val imagem = "https://cidadeinteligenteze.000webhostapp.com/myslim/API/imagens/" + ocorrencia.imagem + ".png"
            val id = ocorrencia.id
            Log.d("AAAAIMMMAGEM", imagem)

            val intent = Intent( context, OcorrenciaEcra::class.java).apply {
                putExtra(EXTRA_MESSAGE, tit )
                putExtra(DESCRICAO, descricao )
                putExtra(LATITIDAO, laitude )
                putExtra(LONGITUSAO, longitude )
                putExtra(ITUSAO, imagem )
                putExtra(IDU, id )
            }
            context.startActivity(intent)


        }*/




        /*bt5.setOnClickListener {
            val context=titulo.context
            val tit= titulo.text.toString()
            val descricao= descricao.text.toString()
            val laitude = ocorrencia.latitude
            val longitude = ocorrencia.longitude
            val imagem = ocorrencia.imagem

            val intent = Intent( context, EditarOcorrencia::class.java).apply {
                putExtra(OCO, tit )
                putExtra(DESCRICAOO, descricao )
                putExtra(LATITIDAO, laitude )
                putExtra(LONGITUSAO, longitude )
                putExtra(IDU, ocorrencia.id )


            }
            context.startActivity(intent)


        }*/


        titulo.text = anuncio.titulo
        preco.text = anuncio.preco


    }

}