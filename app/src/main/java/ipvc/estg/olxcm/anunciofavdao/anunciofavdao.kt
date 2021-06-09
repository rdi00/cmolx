package ipvc.estg.olxcm.anunciofavdao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ipvc.estg.olxcm.Entitie.anunciofav

interface anunciofavdao {
    @Query("select * from anunciovaf order by titulo ASC")
    fun getAll(): LiveData<List<anunciofav>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(anuncio: anunciofav)


    @Query("DELETE FROM anunciovaf where id == :id")
    suspend fun deleteByID(id: Int?)
}