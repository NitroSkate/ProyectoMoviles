package com.example.implantecoclear.Room.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pregunta")
class Pregunta (
    @PrimaryKey
    @ColumnInfo(name = "Q_id")
    var id : Int,
    @ColumnInfo(name = "Q_idSonido")
    var idSonido : Int

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(idSonido)
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