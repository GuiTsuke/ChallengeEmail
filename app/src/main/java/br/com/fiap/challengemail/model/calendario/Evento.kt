package br.com.fiap.challengemail.model.calendario

import androidx.room.*

@Entity(
    tableName = "TBL_EVENTO",
    foreignKeys = [
        ForeignKey(
            entity = Calendario::class,
            parentColumns = ["CD_CALENDARIO"],
            childColumns = ["CD_CALENDARIO"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Evento(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CD_EVENTO")
    val id: Long = 0,

    @ColumnInfo(name = "DS_TITULO")
    val titulo: String,

    @ColumnInfo(name = "DT_INICIO")
    val dataInicio: String,

    @ColumnInfo(name = "DT_FIM")
    val dataFim: String,

    @ColumnInfo(name = "DS_DESCRICAO")
    val descricao: String,

    @ColumnInfo(name = "CD_CALENDARIO")
    val idCalendario: Long
)
