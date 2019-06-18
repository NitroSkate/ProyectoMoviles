package com.example.implantecoclear.Room.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.implantecoclear.Room.Entities.Paciente

@Dao
interface PruebaDao {

    @Query("SELECT * FROM Prueba")
    fun getAllPacientes() : LiveData<List<Paciente>>

    /*@Query("SELECT * FROM Prueba WHERE P_id = (:idPaciente)")
    fun getOnePaciente(idPaciente : Int) : Paciente

    @Query("DELETE FROM Prueba WHERE P_id = (:idPaciente)")
    fun deletePaciente(idPaciente : Int)

    @Insert
    fun addPaciente(paciente : Paciente)

    @Query("UPDATE Prueba SET P_nivel = (:nivel) WHERE P_id = (:idPaciente)")
    fun updatePaciente(idPaciente : Int, nivel : Int)*/

}