package br.com.fiap.challengemail.model.calendario

import androidx.room.*

import androidx.room.*
import br.com.fiap.challengemail.model.usuario.Usuario

@Entity(
    tableName = "TBL_CALENDARIO",
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["CD_USUARIO"],
            childColumns = ["CD_USUARIO"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Calendario(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CD_CALENDARIO")
    val id: Long = 0,

    @ColumnInfo(name = "CD_USUARIO")
    val cdUsuario: Long = 0  // Chave estrangeira para vincular o calendário ao usuário
)

