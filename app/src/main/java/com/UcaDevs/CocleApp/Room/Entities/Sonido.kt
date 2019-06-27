package com.UcaDevs.CocleApp.Room.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "Sonido")
data class Sonido(
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