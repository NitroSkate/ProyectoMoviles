package com.example.implantecoclear.Room.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.implantecoclear.Room.Entities.Sonido

@Dao
interface SonidoDAO {

    @Query("SELECT * FROM Sonido WHERE S_id = (:id)")
    fun getSonido(id : Int) : Sonido

}