package com.polillas.cocleapp.Room.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.polillas.cocleapp.Room.Entities.Pregunta

/*TODO esta interfaz maneja todas las consultas disponibles hacia la tabla pregunta
* */

@Dao
interface PreguntaDAO {
    //TODO Esta consulta se encarga de buscar una pregunta especifica en base a su id
    @Query("SELECT * FROM Pregunta WHERE Q_id = (:id)")
    fun getOnePregunta(id : String) : Pregunta
    //TODO Esta consulta retorna todas las preguntas disponibles en la base de datos
    @Query("SELECT * FROM Pregunta")
    fun getPreguntas() : LiveData<List<Pregunta>>
    //TODO esta consulta se encarga de insertar las nuevas preguntas provenientes de la API
    @Insert
    suspend fun insert(pregunta: Pregunta?)
    //TODO Esta consulta podria utilizarse para limpiar la base de datos local
    @Query("DELETE FROM Pregunta")
    suspend fun nukeQ()

}