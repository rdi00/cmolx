package ipvc.estg.olxcm.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface Endpoints {

    @GET("/anuncios/all")
    fun getAnuncios(): Call<List<Anuncio>>

    @GET("/leiloes/all")
    fun getLeiloes(): Call<List<Leilao>>
}