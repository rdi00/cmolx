package ipvc.estg.olxcm.Entitie;

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anunciovaf")

class anunciofav(
        @PrimaryKey(autoGenerate = true) val id: Int? = null,
        @ColumnInfo(name = "titulo") val nome: String,
        @ColumnInfo(name = "descricao") val descricao: String
)