@file:Suppress("UNREACHABLE_CODE")

package ipvc.estg.olxcm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.olxcm.Adapter.AnuncioAdapter
import ipvc.estg.olxcm.api.Anuncio
import ipvc.estg.olxcm.api.Endpoints
import ipvc.estg.olxcm.api.RespostaAnuncios
import ipvc.estg.olxcm.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ListaAnunciosFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater?.inflate(R.layout.fragment_lista_anuncios, container, false)
        val recyclerView = rootView?.findViewById<RecyclerView>(R.id.recyclerView)
        val request = ServiceBuilder.buildService(Endpoints::class.java)
        val call = request.getAnuncios()


        Log.d("CCCCCCCCC", call.toString())
        call.enqueue(object : Callback<List<Anuncio>> {
            override fun onResponse(call: Call<List<Anuncio>>, response: Response<List<Anuncio>>) {
                Log.d("CCCCCCCCC", "hello")
                if (response.isSuccessful){
                    Log.d("BBBBBBBBBBBB","hello")
                recyclerView?.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@ListaAnunciosFragment.context)
                    adapter = AnuncioAdapter(response.body()!!)
                    Log.d("AAAAAAAAAAAAAAAAA", response.body()!!.toString())

                    }
                }
            }
            override fun onFailure(call: Call<List<Anuncio>>, t: Throwable) {
                Log.d("WWWWWWWW", "$t")
                Toast.makeText(this@ListaAnunciosFragment.context, "$t", Toast.LENGTH_SHORT).show()
            }

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