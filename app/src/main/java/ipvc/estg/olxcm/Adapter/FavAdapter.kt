package ipvc.estg.olxcm.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.olxcm.Entitie.anunciofav
import ipvc.estg.olxcm.ListaFav
import ipvc.estg.olxcm.R

class FavAdapter internal constructor(
    context: ListaFav,
    private val callbackInterface: ListaFav
) : RecyclerView.Adapter<FavAdapter.AnuncioViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var anuncios = emptyList<anunciofav>() // Cached copy of cities

    interface CallbackInterface {
        fun passResultCallback(message: Int?)
    }

    class AnuncioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val anuncioItemView: TextView = itemView.findViewById(R.id.titulololo)
        val anuncioDescricao: TextView = itemView.findViewById(R.id.descricaooooo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnuncioViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerlineanuncio, parent, false)
        return AnuncioViewHolder(itemView)
    }



    internal fun setAnuncios(notas: List<anunciofav>) {
        this.anuncios = notas
        notifyDataSetChanged()
    }



    override fun getItemCount() = anunciofav.size
}