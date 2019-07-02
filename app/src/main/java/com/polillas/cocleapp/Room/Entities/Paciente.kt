package com.polillas.cocleapp.Room.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/*
TODO Esta clase contiene el modelo de la entidad Paciente que sera usado por SQLite al momento de instanciar la
base de datos
*/

//TODO Se coloca un nombre a esta tabla, con este nombre sera guardada en SQLite
@Entity(tableName = "Paciente")
class Paciente(
    // TODO Se le envian como parametros los campos de la tabla, estos tambien sirven para crear la tabla
    @PrimaryKey
    @ColumnInfo(name = "P_id")
    var id: Int,
    @ColumnInfo(name = "P_nombre")
    var nombre : String,
    @ColumnInfo(name = "P_apellido")
    var apellido : String,
    @ColumnInfo(name = "P_ingreso")
    var fechaIngreso : String,
    @ColumnInfo(name = "P_nivel")
    var nivel : Int

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeString(fechaIngreso)
        parcel.writeInt(nivel)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Paciente> {
        override fun createFromParcel(parcel: Parcel): Paciente {
            return Paciente(parcel)
        }

        override fun newArray(size: Int): Array<Paciente?> {
            return arrayOfNulls(size)
        }
    }


}