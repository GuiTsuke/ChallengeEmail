package br.com.fiap.challengemail.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.challengemail.model.email.Email
import br.com.fiap.challengemail.model.usuario.Usuario

@Dao
interface EmailDao {

    @Insert
    fun gravarEmail(email: Email): Long

    @Query("SELECT * FROM TBL_EMAIL WHERE DS_REMETENTE = :email OR DS_DESTINATARIO = :email ORDER BY DT_ENVIO ASC")
    fun getAllEmails(email: String): List<Email>

    @Delete
    fun deleteEmail(email: Email): Int

    @Query("SELECT * FROM TBL_EMAIL" +
            " WHERE CD_EMAIL = :id")
    fun getEmailById(id: Int): Email


}