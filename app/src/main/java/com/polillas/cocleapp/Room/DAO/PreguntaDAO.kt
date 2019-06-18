package com.polillas.cocleapp.Room.DAO

import androidx.room.Dao
import androidx.room.Query
import com.polillas.cocleapp.Room.Entities.Pregunta

@Dao
interface PreguntaDAO {

    @Query("SELECT * FROM Pregunta WHERE Q_id = (:id)")
    fun getOnePregunta(id : Int) : Pregunta

}