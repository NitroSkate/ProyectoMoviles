package com.polillas.cocleapp.Room.Entities

import com.squareup.moshi.Json

//TODO este modelo es el responsable de obtener las preguntas consu sonido correcto y ademas de eso el nivel al que corresponde

data class ResponsePreg (
    @field:Json(name = "id")
    var id: String,
    @field:Json(name = "idSonido")
    var idSonido: Sonido,
    @field:Json(name = "nivel")
    var nivel: String
)