package com.polillas.cocleapp.Room.DAO

import androidx.room.Dao
import androidx.room.Query
import com.polillas.cocleapp.Room.Entities.Sonido

@Dao
interface SonidoDAO {

    @Query("SELECT * FROM Sonido WHERE S_id = (:id)")
    fun getSonido(id : Int) : Sonido

}