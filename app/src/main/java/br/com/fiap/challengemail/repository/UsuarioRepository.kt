package br.com.fiap.challengemail.repository

import android.content.Context
import br.com.fiap.challengemail.db.connect.ChallengeMailDB
import br.com.fiap.challengemail.model.usuario.Usuario

class UsuarioRepository(context: Context) {
    private val db = ChallengeMailDB.getDatabase(context).usuarioDao()

    fun createUser(usuario: Usuario): String {
        if (db.getUserByEmail(usuario.email) == null) {
            db.createUser(usuario)
            return "Usuario cadastrado com sucesso"
        } else {
            return "Email ja cadastrado"
        }
    }


    fun getAllUsers(): List<Usuario> {
        return db.getAllUsers()
    }

    fun getUserByEmail(email: String): Usuario {
        return if (db.getUserByEmail(email) != null) {
            db.getUserByEmail(email)!!
        } else {
            Usuario()
        }
    }

    fun getUserById(id: Int): Usuario {
        return db.getUserById(id)
    }

    fun delete(usuario: Usuario): Int {
        return db.deleteUser(usuario)
    }
}