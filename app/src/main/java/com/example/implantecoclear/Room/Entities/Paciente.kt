package com.example.implantecoclear.Room.Entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Paciente")
class Paciente(
    @ColumnInfo(name = "P_nombre")
    var nombre : String,
    @ColumnInfo(name = "P_nivel")
    var nivel : Int,

):Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "P_id")
    var id : Int = 0
}