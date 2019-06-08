package com.example.implantecoclear.Room.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pregunta")
class Pregunta (

    @ColumnInfo(name = "Q_pregunta")
    var pregunta : String,
    @ColumnInfo(name = "Q_respuesta")
    var respuesta : Int,
    @ColumnInfo(name = "Q_idSonido")
    var idSonido : Int,
    @ColumnInfo(name = "Q_rSonido")
    var rSonido : String,
    @ColumnInfo(name = "Q_rSonido")
    var idImg : Int,
    @ColumnInfo(name = "Q_rImg")
    var rImg : String

) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Q_id")
    var id : Int = 0

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString()) {
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(pregunta)
        parcel.writeInt(respuesta)
        parcel.writeInt(idSonido)
        parcel.writeString(rSonido)
        parcel.writeInt(idImg)
        parcel.writeString(rImg)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pregunta> {
        override fun createFromParcel(parcel: Parcel): Pregunta {
            return Pregunta(parcel)
        }

        override fun newArray(size: Int): Array<Pregunta?> {
            return arrayOfNulls(size)
        }
    }

}