package com.example.implantecoclear.Room.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.implantecoclear.Room.DAO.PacienteDAO
import com.example.implantecoclear.Room.DAO.PreguntaDAO
import com.example.implantecoclear.Room.DAO.TerapistaDAO
import com.example.implantecoclear.Room.Entities.Paciente
import com.example.implantecoclear.Room.Entities.Pregunta
import com.example.implantecoclear.Room.Entities.Terapista

@Database(entities = arrayOf(Paciente::class, Pregunta::class, Terapista::class), version = 1)
public abstract class CoclearDatabase : RoomDatabase(){

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