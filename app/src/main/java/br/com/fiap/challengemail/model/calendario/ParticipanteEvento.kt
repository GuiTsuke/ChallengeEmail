package br.com.fiap.challengemail.model.calendario

import androidx.room.*
import br.com.fiap.challengemail.model.usuario.Usuario

@Entity(
    tableName = "TBL_PARTICIPANTE_EVENTO",
    foreignKeys = [
        ForeignKey(
            entity = Evento::class,
            parentColumns = ["CD_EVENTO"],
            childColumns = ["CD_EVENTO"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["CD_USUARIO"],
            childColumns = ["CD_USUARIO"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["CD_EVENTO"]),
        Index(value = ["CD_USUARIO"])
    ],
    primaryKeys = ["CD_EVENTO", "CD_USUARIO"]
)
data class ParticipanteEvento(
    @ColumnInfo(name = "CD_EVENTO")
    val idEvento: Long,

    @ColumnInfo(name = "CD_USUARIO")
    val idUsuario: Long
)
