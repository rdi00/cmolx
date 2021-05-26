package ipvc.estg.olxcm.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface Endpoints {
    @GET("/myslim/API/ocorrencias")
    fun getAnuncios(): Call<List<Anuncio>>

}