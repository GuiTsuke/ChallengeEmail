package br.com.fiap.challengemail.db.connect

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.challengemail.dao.UsuarioDao
import br.com.fiap.challengemail.model.usuario.Usuario

@Database(
    entities = [Usuario::class],
    version = 2

)
abstract class ChallengeMailDB: RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    companion object {

        private lateinit var instance: ChallengeMailDB

        fun getDatabase(context: Context): ChallengeMailDB {
            if (!::instance.isInitialized) {
                instance =
                    Room.databaseBuilder(
                        context,
                        ChallengeMailDB::class.java,
                        "ChallengeMailDB"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return instance

        }
    }
}