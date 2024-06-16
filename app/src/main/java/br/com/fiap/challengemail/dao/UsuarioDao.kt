package br.com.fiap.challengemail.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.challengemail.model.usuario.Usuario

@Dao
interface UsuarioDao {
    @Insert
    fun createUser(usuario: Usuario): Long

    @Query("SELECT * FROM TBL_USUARIOS ORDER BY DS_NOME ASC")
    fun getAllUsers(): List<Usuario>

    @Delete
    fun deleteUser(usuario: Usuario): Int

    @Query("SELECT * FROM TBL_USUARIOS" +
            " WHERE DS_EMAIL = :email")
    fun getUserByEmail(email: String): Usuario?

    @Query("SELECT * FROM TBL_USUARIOS WHERE CD_USUARIO = :id")
    fun getUserById(id: Int): Usuario
}