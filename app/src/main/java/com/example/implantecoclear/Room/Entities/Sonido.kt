package com.example.implantecoclear.Room.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Sonido")
class Sonido(
    @PrimaryKey
    @ColumnInfo(name = "S_id")
    var id : Int,
    @ColumnInfo(name = "S_ruta")
    var rutaSonido : String,
    @ColumnInfo(name = "S_ImgRuta")
    var rutaImagen : String
){
}