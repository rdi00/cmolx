@file:Suppress("UNREACHABLE_CODE")

package ipvc.estg.olxcm

import LeilaoAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.olxcm.api.Endpoints
import ipvc.estg.olxcm.api.Leilao
import ipvc.estg.olxcm.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ListaLeiloesFragment : Fragment() {

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
        val rootView = inflater?.inflate(R.layout.fragment_lista_leiloes, container, false)
        val recyclerView = rootView?.findViewById<RecyclerView>(R.id.recyclerView2)
        val request = ServiceBuilder.buildService(Endpoints::class.java)
        val call = request.getLeiloes()

        call.enqueue(object : Callback<List<Leilao>> {
            override fun onResponse(call: Call<List<Leilao>>, response: Response<List<Leilao>>) {
                if (response.isSuccessful){

                    recyclerView?.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@ListaLeiloesFragment.context)
                        adapter = LeilaoAdapter(response.body()!!)
                    }
                }
            }
            override fun onFailure(call: Call<List<Leilao>>, t: Throwable) {
                Toast.makeText(this@ListaLeiloesFragment.context, "erro?", Toast.LENGTH_SHORT).show()
            }
        })
        return rootView
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListaLeiloesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}