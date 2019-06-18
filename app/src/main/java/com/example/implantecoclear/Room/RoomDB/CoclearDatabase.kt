package com.example.implantecoclear.Room.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.implantecoclear.Room.DAO.*
import com.example.implantecoclear.Room.Entities.*

@Database(entities = arrayOf(Paciente::class, Pregunta::class, Terapista::class, Sonido::class, Prueba::class), version = 1)
public abstract class CoclearDatabase : RoomDatabase(){

    abstract fun pruebaDAO() : PruebaDao
    abstract fun sonidoDAO() : SonidoDAO
    abstract fun pacienteDAO() : PacienteDAO
    abstract fun preguntaDAO() : PreguntaDAO
    abstract fun terapistaDAO() : TerapistaDAO

    companion object{
        @Volatile
        private var INSTANCE : CoclearDatabase? = null

        fun getDatabase(context: Context): CoclearDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CoclearDatabase::class.java,
                        "CoclearDB"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}