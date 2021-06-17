package ipvc.estg.olxcm.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.util.ArrayList

interface Endpoints {

    @GET("/anuncios/all")
    fun getAnuncios(): Call<List<Anuncio>>

    @GET("/leiloes/all")
    fun getLeiloes(): Call<List<Leilao>>



    @POST("/updatelance/:id/:id_comprador/:valor_atual")
    fun lance(@Path ("id") id: Int,@Path ("id_comprador") id_comprador: Int,
              @Path("valor_atual") valor_atual:String): Call<Leilao>


    @GET("/leilao")
    fun leilao(@Query ("id") id: Int): Call<Leilao>

    @FormUrlEncoded
    @POST("/login")
    fun login(@Field("username") username: String?,@Field("password") password: String?): Call<Utilizador>
}