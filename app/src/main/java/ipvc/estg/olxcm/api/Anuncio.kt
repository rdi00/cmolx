package ipvc.estg.olxcm.api

data class Anuncio(
    val id: Int,
    val titulo: String,
    val desricao: String,
    val preco: String,
    val imagem: String,
    val utilizador_id: Int,
    val latitude: String,
    val longitude: String,
    val data: String
)