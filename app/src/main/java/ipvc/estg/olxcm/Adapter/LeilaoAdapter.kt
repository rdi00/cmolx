import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.olxcm.LeilaoEcra
import ipvc.estg.olxcm.R
import ipvc.estg.olxcm.api.Anuncio
import ipvc.estg.olxcm.api.Leilao

class LeilaoAdapter(val leiloes: List<Leilao>): RecyclerView.Adapter<LeiloesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeiloesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerlineleilao, parent, false)
        return LeiloesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return leiloes.size
    }

    override fun onBindViewHolder(holder: LeiloesViewHolder, position: Int) {
        return holder.bind(leiloes[position])
    }

}

class LeiloesViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){



    private val titulo: TextView = itemView.findViewById(R.id.tituloLeilao)

    private val data: TextView = itemView.findViewById(R.id.dataFinalLeilao)
    private val preco: TextView = itemView.findViewById(R.id.valorInicialLeilao)

    val bt4: ConstraintLayout = itemView.findViewById((R.id.lineline2))

    fun bind(leilao: Leilao) {

         bt4.setOnClickListener {
             val context=titulo.context
             val tit= leilao.titulo
             val data= leilao.data_fim
             val preco = leilao.valor_inicial
             val lance_atual = leilao.valor_atual
             val imagem = leilao.imagem
             val id = leilao.id
             val user_id = leilao.utilizador_id
             val comprador = leilao.id_comprador


             val intent = Intent( context, LeilaoEcra::class.java).apply {
                 putExtra("TIT", tit )
                 putExtra("DATA", data)
                 putExtra("PRECO", preco )
                 putExtra("LANCE", lance_atual )
                 putExtra("IMG", imagem )
                 putExtra("ID", id )
                 putExtra("VENDEDOR", user_id )
                 putExtra("COMPRADOR", comprador)
             }
             context.startActivity(intent)


         }


        data.text = leilao.data_fim
        preco.text = leilao.valor_inicial


    }

}