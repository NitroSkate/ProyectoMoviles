package com.polillas.cocleapp.Room.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Prueba")
class Prueba(
    @PrimaryKey
    @ColumnInfo(name = "P_id")
    var id : Int,
    @ColumnInfo(name = "P_nombre")
    var nombre : String,
    @ColumnInfo(name = "P_apellido")
    var apellido : String,
    @ColumnInfo(name = "P_paciente")
    var paciente : Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeInt(paciente)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Prueba> {
        override fun createFromParcel(parcel: Parcel): Prueba {
            return Prueba(parcel)
        }

        override fun newArray(size: Int): Array<Prueba?> {
            return arrayOfNulls(size)
        }
    }

}