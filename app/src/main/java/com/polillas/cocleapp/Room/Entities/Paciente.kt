package com.polillas.cocleapp.Room.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Paciente")
class Paciente(
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