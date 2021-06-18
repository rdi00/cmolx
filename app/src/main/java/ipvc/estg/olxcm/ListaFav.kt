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


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ListaFav : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var vm: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View?{
        val rootView = inflater?.inflate(R.layout.fragment_lista_fav, container, false)

        // recycler view
        val recyclerView = rootView?.findViewById<RecyclerView>(R.id.recyclerViewFav)
        val adapter = FavAdapter(this,this)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(this@ListaFav.context)

        // view model
        vm = ViewModelProvider(this).get(ViewModel::class.java)
        vm.allNotas.observe(viewLifecycleOwner, Observer { anunciofav ->
            // Update the cached copy of the words in the adapter.
            anunciofav?.let { adapter.setAnuncios(it) }
        })

        return rootView

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListaAnunciosFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}