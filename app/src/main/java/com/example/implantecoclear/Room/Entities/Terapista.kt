package com.example.implantecoclear.Room.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Terapista")
class Terapista(
    @ColumnInfo(name = "T_nombre")
    var nombre : String,
    @ColumnInfo(name = "T_apellido")
    var apellido : String,
    @ColumnInfo(name = "T_paciente")
    var paciente : Int
) : Parcelable{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "T_id")
    var id : Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeInt(paciente)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Terapista> {
        override fun createFromParcel(parcel: Parcel): Terapista {
            return Terapista(parcel)
        }

        override fun newArray(size: Int): Array<Terapista?> {
            return arrayOfNulls(size)
        }
    }
}