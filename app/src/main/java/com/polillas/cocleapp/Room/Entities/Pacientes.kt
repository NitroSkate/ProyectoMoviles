package com.polillas.cocleapp.Room.Entities

data class Pacientes (
    var id: String?,
    var nombre : String?,
    var apellido : String?,
    var fechaIngreso : String?,
    var nivel : String?,
    var puntajes : List<Puntaje>?
)