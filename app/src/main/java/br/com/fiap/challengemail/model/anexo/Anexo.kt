package br.com.fiap.challengemail.model.anexo

import androidx.room.*
import br.com.fiap.challengemail.model.email.Email

@Entity(
    tableName = "TBL_ANEXO",
    foreignKeys = [
        ForeignKey(
            entity = Email::class,
            parentColumns = ["CD_EMAIL"],
            childColumns = ["CD_EMAIL"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Anexo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CD_ANEXO")
    val id: Long = 0,

    @ColumnInfo(name = "DS_NOME")
    val nome: String = "",

    @ColumnInfo(name = "TX_CONTEUDO")
    val conteudo: ByteArray = byteArrayOf(),

    @ColumnInfo(name = "CD_EMAIL")
    val cdEmail: Long = 0
)

