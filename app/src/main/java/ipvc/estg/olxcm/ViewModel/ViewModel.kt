package ipvc.estg.olxcm.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ipvc.estg.olxcm.Entitie.anunciofav
import ipvc.estg.olxcm.db.anunciofavDB
import ipvc.estg.olxcm.db.anunciofavrepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: anunciofavrepo
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allNotas: LiveData<List<anunciofav>>

    init {
        val notaDao = anunciofavDB.getDatabase(application, viewModelScope).anunciofavdao()
        repository = anunciofavrepo(notaDao)
        allNotas = repository.allNotas
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(anuncio: anunciofav) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(anuncio)
    }


    // delete by nome
    fun deleteByID(nota: Int?) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteByID(nota)
    }



}