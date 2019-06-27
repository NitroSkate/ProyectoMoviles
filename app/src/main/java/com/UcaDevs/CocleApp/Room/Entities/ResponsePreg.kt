package com.UcaDevs.CocleApp.Room.Entities

import com.squareup.moshi.Json

data class ResponsePreg (
    @field:Json(name = "id")
    var id: String,
    @field:Json(name = "idSonido")
    var idSonido: Sonido,
    @field:Json(name = "nivel")
    var nivel: String
)