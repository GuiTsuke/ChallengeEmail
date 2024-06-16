package br.com.fiap.challengemail.model.email

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TBL_CATEGORIA_EMAIL")
data class Categoria(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CD_CATEGORIA")
    val id: Long = 0,

    @ColumnInfo(name = "DS_NOME")
    val nome: String = ""
)