package com.polillas.cocleapp.Room.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.polillas.cocleapp.Room.Entities.Pregunta

@Dao
interface PreguntaDAO {

    @Query("SELECT * FROM Pregunta WHERE Q_id = (:id)")
    fun getOnePregunta(id : String) : Pregunta
    
    @Query("SELECT * FROM Pregunta")
    fun getPreguntas() : LiveData<List<Pregunta>>

    @Insert
    suspend fun insert(pregunta: Pregunta?)
}