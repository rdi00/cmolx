package ipvc.estg.olxcm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipvc.estg.olxcm.Adapter.AnuncioAdapter
import ipvc.estg.olxcm.api.Anuncio
import ipvc.estg.olxcm.api.Endpoints
import ipvc.estg.olxcm.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListaAnuncios : AppCompatActivity()  {

    private val newWordActivityRequestCode = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_anuncios)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val request = ServiceBuilder.buildService(Endpoints::class.java)
        val call = request.getAnuncios()

        call.enqueue(object : Callback<List<Anuncio>> {
            override fun onResponse(call: Call<List<Anuncio>>, response: Response<List<Anuncio>>) {
                if (response.isSuccessful){

                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@ListaAnuncios)
                        adapter =AnuncioAdapter(response.body()!!)
                    }
                }
            }
            override fun onFailure(call: Call<List<Anuncio>>, t: Throwable) {
                Toast.makeText(this@ListaAnuncios, "erro?", Toast.LENGTH_SHORT).show()
            }
        })


    }






}

