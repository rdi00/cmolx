package ipvc.estg.olxcm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.olxcm.Adapter.FavAdapter
import ipvc.estg.olxcm.ViewModel.ViewModel

class ListaFav : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var ViewModel: ViewModel



    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View?{
        val rootView = inflater?.inflate(R.layout.fragment_lista_fav, container, false)


        // recycler view
        val recyclerView = rootView?.findViewById<RecyclerView>(R.id.recyclerViewFav)
        val adapter = FavAdapter(this,this)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(this@ListaFav.context)

        // view model
        ViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        ViewModel.allNotas.observe(viewLifecycleOwner, Observer { anunciofav ->
            // Update the cached copy of the words in the adapter.
            anunciofav?.let { adapter.setAnuncios(it) }
        })

        return rootView

    }
}