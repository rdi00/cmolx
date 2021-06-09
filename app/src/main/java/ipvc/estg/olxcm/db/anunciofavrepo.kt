package ipvc.estg.olxcm.db

import androidx.lifecycle.LiveData
import ipvc.estg.olxcm.anunciofavdao.anunciofavdao
import ipvc.estg.olxcm.Entitie.anunciofav

class anunciofavrepo(private val anunciofavdao: anunciofavdao) {

    val allNotas: LiveData<List<anunciofav>> = anunciofavdao.getAll()

    suspend fun insert(nota: anunciofav) {
        anunciofavdao.insert(nota)
    }


    suspend fun deleteByID(id: Int?){
        anunciofavdao.deleteByID(id)
    }
}