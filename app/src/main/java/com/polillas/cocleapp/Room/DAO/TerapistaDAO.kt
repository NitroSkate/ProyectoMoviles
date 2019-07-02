package com.polillas.cocleapp.Room.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.polillas.cocleapp.Room.Entities.Terapeuta


@Dao
interface TerapistaDAO {

    @Insert
    fun insert(terapista : Terapeuta)

    @Query("DELETE FROM Terapeuta WHERE T_id = (:ID)")
    fun deleteTerapista(ID : Int)


}