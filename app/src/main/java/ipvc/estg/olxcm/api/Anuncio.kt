package ipvc.estg.olxcm.api

data class Anuncio(
    val id: Int,
    val titulo: String,
    val descricao: String,
    val preco: String,
    val imagem: String,
    val utilizador_id: Int,
    val localizacao: String,
    val data: String
)