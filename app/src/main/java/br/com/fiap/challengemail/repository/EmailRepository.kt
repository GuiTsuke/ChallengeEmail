package br.com.fiap.challengemail.repository

import android.content.Context
import br.com.fiap.challengemail.db.connect.ChallengeMailDB
import br.com.fiap.challengemail.model.email.Email
import br.com.fiap.challengemail.model.usuario.Usuario

class EmailRepository(context: Context) {
    private val db = ChallengeMailDB.getDatabase(context).emailDao()

    fun createEmail(email: Email) {
        db.gravarEmail(email)
    }

    fun getAllEmails(email: String): List<Email> {
        if (db.getAllEmails(email).isEmpty()) {
            return listOf()
        } else {
            return db.getAllEmails(email)
        }
    }

    fun getEmailById(id: Int): Email {
        return db.getEmailById(id)
    }

    fun deleteEmail(email: Email): Int {
        return db.deleteEmail(email)
    }

}