package br.com.fiap.challengemail.repository

import android.content.Context
import br.com.fiap.challengemail.dao.UsuarioDao
import br.com.fiap.challengemail.db.connect.ChallengeMailDB

class AuthRepository(context: Context) {

    private val usuarioRepository = UsuarioRepository(context)

        fun autenticarUsuario(email: String, senha: String): Boolean {
            val usuario = usuarioRepository.getUserByEmail(email)
            return usuario.email == email && usuario.password == senha
        }

}
