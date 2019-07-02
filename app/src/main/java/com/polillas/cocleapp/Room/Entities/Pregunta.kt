package com.polillas.cocleapp.Room.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
/*
TODO Esta clase contiene el modelo de la entidad Pregunta que sera usado por SQLite al momento de instanciar la
base de datos
*/

//TODO se define el nombre con el que SQLite almacenara y referenciara la tabla paciente
@Entity(tableName = "Pregunta")
data class Pregunta (
    //TODO Se definen los campos de la tabla ademas se colocan como parametros del constructor de la clase
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

