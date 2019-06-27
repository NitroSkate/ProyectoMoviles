package com.polillas.cocleapp.Room.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.polillas.cocleapp.Room.Entities.Terapista


@Dao
interface TerapistaDAO {

    @Insert
    fun insert(terapista : Terapista)

    @Query("DELETE FROM Terapista WHERE T_id = (:ID)")
    fun deleteTerapista(ID : Int)


}