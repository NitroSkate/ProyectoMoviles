package com.UcaDevs.CocleApp.Room.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.UcaDevs.CocleApp.Room.Entities.Terapista


@Dao
interface TerapistaDAO {

    @Insert
    fun insert(terapista : Terapista)

    @Query("DELETE FROM Terapista WHERE T_id = (:ID)")
    fun deleteTerapista(ID : Int)


}