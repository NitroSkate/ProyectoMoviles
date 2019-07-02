package com.polillas.cocleapp.Room.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.polillas.cocleapp.Room.DAO.*
import com.polillas.cocleapp.Room.Entities.*

@Database(entities = arrayOf(Pregunta::class/*, Paciente::class, Terapeuta::class, Sonido::class*/, Sonido::class), version = 1, exportSchema = false)
public abstract class CoclearDatabase : RoomDatabase(){

    //abstract fun pruebaDAO() : PruebaDao
    abstract fun sonidoDAO() : SonidoDAO
    //abstract fun pacienteDAO() : PacienteDAO
    abstract fun preguntaDAO() : PreguntaDAO
    //abstract fun terapistaDAO() : TerapistaDAO

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
                        context,
                        CoclearDatabase::class.java,
                        "CoclearDB"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}