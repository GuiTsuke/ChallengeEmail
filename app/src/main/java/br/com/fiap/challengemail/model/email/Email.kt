package br.com.fiap.challengemail.model.email

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "TBL_EMAIL"
)
data class Email(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CD_EMAIL")
    val id: Int = 0,

    @ColumnInfo(name = "DS_ASSUNTO")
    val assunto: String = "",

    @ColumnInfo(name = "DS_CORPO")
    val corpo: String = "",

    @ColumnInfo(name = "DT_ENVIO")
    val dataEnvio: String = "",

    @ColumnInfo(name = "DS_DESTINATARIO")
    val destinatario: String = "",

    @ColumnInfo(name = "DS_REMETENTE")
    val remetente: String = "",

    @ColumnInfo(name = "FL_LIDO")
    val isRead: Boolean = false,

    @ColumnInfo(name = "FL_FAVORITO")
    val isFavorite: Boolean = false,

    @ColumnInfo(name = "FL_DELETADO")
    var isDeleted: Boolean = false,

    @ColumnInfo(name = "DS_LIST_TAGS")
    var tags: String = ""
)


