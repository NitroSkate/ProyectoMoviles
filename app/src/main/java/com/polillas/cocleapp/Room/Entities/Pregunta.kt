package com.polillas.cocleapp.Room.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "Pregunta")
data class Pregunta (
    @PrimaryKey
    @ColumnInfo(name = "Q_id")
    @field:Json(name = "_id")
    var id : String,
    /*@ColumnInfo(name = "Q_idSonido")
    @field:Json(name = "idSonido")
    var idSonido : Sonido,*/
    @ColumnInfo(name = "Q_nivel")
    @field:Json(name = "nivel")
    var nivel : String

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(nivel)
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

