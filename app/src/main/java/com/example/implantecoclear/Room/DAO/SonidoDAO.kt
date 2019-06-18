package com.example.implantecoclear.Room.DAO

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SonidoDAO {

    @Query("SELECT * FROM Sonido WHERE S_id = (:id)")
    fun getPregunta(id : Int)

}