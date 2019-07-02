package com.polillas.cocleapp.Room.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.polillas.cocleapp.Room.DAO.*
import com.polillas.cocleapp.Room.Entities.*

/*TODO esta clase se encarga de hacer la instancia de la base de datos*/

@Database(entities = arrayOf(Pregunta::class, Sonido::class), version = 1, exportSchema = false)
abstract class CoclearDatabase : RoomDatabase(){

    //TODO se crean instancias de los DAO a utilizar
    abstract fun sonidoDAO() : SonidoDAO
    abstract fun preguntaDAO() : PreguntaDAO

    //TODO aqui se verifica si la base de datos ya esta creada, de ser asi solo se retorna esa instancia de lo contrario se crea una nueva
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