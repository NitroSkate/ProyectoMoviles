package com.example.implantecoclear.Room.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Sonido")
class Sonido(

    @ColumnInfo(name = "S_ruta")
    var ruta : String

) : Parcelable
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "S_id")
    var id : Int = 0

    constructor(parcel: Parcel) : this(parcel.readString()) {
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ruta)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Sonido> {
        override fun createFromParcel(parcel: Parcel): Sonido {
            return Sonido(parcel)
        }

        override fun newArray(size: Int): Array<Sonido?> {
            return arrayOfNulls(size)
        }
    }
}