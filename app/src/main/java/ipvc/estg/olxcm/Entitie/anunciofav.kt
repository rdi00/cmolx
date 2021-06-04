package ipvc.estg.olxcm.Entitie;

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anunciovaf")

class anunciofav(
        @PrimaryKey(autoGenerate = true) val id: Int? = null,
        @ColumnInfo(name = "titulo") val nome: String,
        @ColumnInfo(name = "descricao") val descricao: String,
        @ColumnInfo(name = "latitude") val latitude: String,
        @ColumnInfo(name = "longitude") val longitude: String,
        @ColumnInfo(name = "contacto") val contacto: String,
        @ColumnInfo(name = "utilizador") val utilizador: String,
        @ColumnInfo(name = "data") val data: String,
)