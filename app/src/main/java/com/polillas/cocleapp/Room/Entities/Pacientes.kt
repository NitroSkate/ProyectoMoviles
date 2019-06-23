package com.polillas.cocleapp.Room.Entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Pacientes (
    var id: String?,
    var nombre : String?,
    var apellido : String?,
    var fechaIngreso : String?,
    var nivel : String?,
    var puntajes : List<Puntaje>?
)