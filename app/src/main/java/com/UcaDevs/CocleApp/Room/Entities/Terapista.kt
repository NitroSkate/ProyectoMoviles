package com.UcaDevs.CocleApp.Room.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Terapista")
class Terapista(
    @PrimaryKey
    @ColumnInfo(name = "T_id")
    var id : Int,
    @ColumnInfo(name = "T_nombre")
    var nombre : String,
    @ColumnInfo(name = "T_apellido")
    var apellido : String,
    @ColumnInfo(name = "T_nTerapista")
    var numero : Int,
    @ColumnInfo(name = "T_correo")
    var correo : String,
    @ColumnInfo(name = "T_password")
    var password : String,
    @ColumnInfo(name = "T_activo")
    var activo : Boolean
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeInt(numero)
        parcel.writeString(correo)
        parcel.writeString(password)
        parcel.writeByte(if (activo) 1 else 0)
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