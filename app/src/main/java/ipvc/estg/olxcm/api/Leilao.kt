package ipvc.estg.olxcm.api

import java.io.Serializable

class Leilao(

val id: Int,
val titulo: String,
val valor_inicial: String,
val data_fim: String,
val valor_atual: String,
val valor_final: String,
val utilizador_id: Int,
val imagem: String,
val id_comprador: Int,

):Serializable
