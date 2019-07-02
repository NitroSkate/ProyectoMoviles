package com.polillas.cocleapp.Room.Entities

//TODO este modelo es la base para retrofit, contiene todos los campos que retornara retrofit
data class Pacientes (
    var id: String?,
    var nombre : String?,
    var apellido : String?,
    var fechaIngreso : String?,
    var nivel : String?,
    var puntajes : List<Puntaje>?
)