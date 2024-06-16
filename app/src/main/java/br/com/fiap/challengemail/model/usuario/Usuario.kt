package br.com.fiap.challengemail.model.usuario

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "TBL_USUARIOS",
        indices = [Index(value = ["DS_EMAIL"], unique = true)]
    )
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CD_USUARIO")
    val id: Int = 0,

    @ColumnInfo(name = "DS_NOME")
    val name: String = "",

    @ColumnInfo(name = "DS_EMAIL")
    val email: String = "",

    @ColumnInfo(name = "DS_PASSWORD")
    val password: String = "",

    @ColumnInfo(name = "DT_CADASTRO", defaultValue = "2024-01-01")
    val dataCadastro: String = ""
)