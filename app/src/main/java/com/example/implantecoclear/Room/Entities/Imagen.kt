package com.example.implantecoclear.Room.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Imagen")
class Imagen(

    @ColumnInfo(name = "I_ruta")
    var ruta : String

) : Parcelable
{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "I_id")
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

    companion object CREATOR : Parcelable.Creator<Imagen> {
        override fun createFromParcel(parcel: Parcel): Imagen {
            return Imagen(parcel)
        }

        override fun newArray(size: Int): Array<Imagen?> {
            return arrayOfNulls(size)
        }
    }

}