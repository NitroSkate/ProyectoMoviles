package com.polillas.cocleapp.Room.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.polillas.cocleapp.Room.Entities.Sonido

/*TODO Esta interfaz se encarga de manejar todas las posibles consultas a la tabla sonido que realmente
* contiene todos los recursos*/

@Dao
interface SonidoDAO {
    //TODO esta consulta se encarga de obtener un solo sonido en base a un ID
    @Query("SELECT * FROM Sonido WHERE S_id = (:id)")
    fun getSonido(id : String) : Sonido
    //TODO esta consulta se encarga de obtener todos los sonidos almacenados en la base de datos
    @Query("SELECT * FROM Sonido")
    fun getAllSonidos(): LiveData<List<Sonido>>
    //TODO esta consulta funciona para insertar los nuevos recursos provenientes de la API a la base de datos
    @Insert
    suspend fun insertSound(sonido : Sonido?)
    //TODO esta funcion sirve para limpiar la base de datos
    @Query("DELETE FROM Sonido")
    suspend fun nukeS()

}