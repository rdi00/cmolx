package ipvc.estg.olxcm.Entitie;

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anunciovaf")

class anunciofav(
        @PrimaryKey(autoGenerate = true) val id: Int? = null,
        @ColumnInfo(name = "titulo") val titulo: String?,
        @ColumnInfo(name = "descricao") val descricao: String?,
        @ColumnInfo(name = "preco") val preco: String?,
        @ColumnInfo(name = "localizacao") val localizacao: String?,
        @ColumnInfo(name = "contacto") val contacto: String?,
        @ColumnInfo(name = "utilizador") val utilizador: String?,
        @ColumnInfo(name = "data") val data: String?,
)