 package com.polillas.cocleapp.Room.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.polillas.cocleapp.Room.Entities.Paciente

 /*TODO esta interfaz maneja todas las posibles consultas que se deben o deberian realizar hacia la base de datos
 con respecto a la tabla paciente*/
@Dao
interface PacienteDAO {

     //TODO esta consulta es necesaria para obtener todos los pacientes listados en la base de datos
    @Query("SELECT * FROM Paciente")
    fun getAllPacientes() : LiveData<List<Paciente>>
    //TODO esta consulta funciona para obtener exclusivamente un paciente mediante su id
    @Query("SELECT * FROM Paciente WHERE P_id = (:idPaciente)")
    fun getOnePaciente(idPaciente : Int) : Paciente
    //TODO esta consulta permite borrar usuarios si asi se desea, esto mediante su id
    @Query("DELETE FROM Paciente WHERE P_id = (:idPaciente)")
    fun deletePaciente(idPaciente : Int)
    //TODO esta consulta funciona para poder añadir nuevos pacientes a la coleccion de datos
    @Insert
    fun addPaciente(paciente : Paciente)
    //TODO mediante esta consulta se puede actualizar el nivel del paciente
    @Query("UPDATE Paciente SET P_nivel = (:nivel) WHERE P_id = (:idPaciente)")
    fun updatePaciente(idPaciente : Int, nivel : Int)

}