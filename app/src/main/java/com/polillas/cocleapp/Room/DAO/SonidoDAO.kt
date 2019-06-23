package com.polillas.cocleapp.Room.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.polillas.cocleapp.Room.Entities.Sonido

@Dao
interface SonidoDAO {

    @Query("SELECT * FROM Sonido WHERE S_id = (:id)")
    fun getSonido(id : String) : Sonido

    @Query("SELECT * FROM Sonido")
    fun getAllSonidos(): LiveData<List<Sonido>>

    @Insert
    suspend fun insertSound(sonido : Sonido?)

}