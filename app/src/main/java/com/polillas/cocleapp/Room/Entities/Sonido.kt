package com.polillas.cocleapp.Room.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
/*TODO En esta clase se crea el modelo de la tabla Sonido que contendra los recursos audiovisuales de la app*/

//TODO Se asigna el nombre con el que sera guardada esta tabla en SQLite
@Entity(tableName = "Sonido")
data class Sonido(
    //TODO esta tabla contiene los campos necesarios para poder relacionar un sonido a una imagen o viceversa
    @PrimaryKey
    @ColumnInfo(name = "S_id")
    @field:Json(name = "_id")
    var id : String,
    @ColumnInfo(name = "S_ruta")
    @field:Json(name = "ruta")
    var rutaSonido : String,
    @ColumnInfo(name = "S_ImgRuta")
    @field:Json(name = "rutaImg")
    var rutaImagen : String
){
}