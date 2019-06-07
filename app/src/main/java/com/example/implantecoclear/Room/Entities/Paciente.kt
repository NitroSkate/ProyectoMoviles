package com.example.implantecoclear.Room.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Paciente")
class Paciente(

    @ColumnInfo(name = "P_nombre")
    var nombre : String,
    @ColumnInfo(name = "P_apellido")
    var apellido : String,
    @ColumnInfo(name = "P_ingreso")
    var fechaIngreso : String,
    @ColumnInfo(name = "P_nivel")
    var nivel : Int

): Parcelable
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "P_id")
    var id : Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeString(fechaIngreso)
        parcel.writeInt(nivel)
        parcel.writeInt(id)
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