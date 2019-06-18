package com.example.implantecoclear.Room.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "Prueba")
class Prueba(
    @ColumnInfo(name = "T_nombre")
    var nombre : String,
    @ColumnInfo(name = "T_apellido")
    var apellido : String,
    @ColumnInfo(name = "T_paciente")
    var paciente : Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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