package com.polillas.cocleapp.Room.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.polillas.cocleapp.Room.Entities.Sonido

class GameViewModel(application: Application): AndroidViewModel(application){

    private lateinit var respuesta: Sonido
    private var actual: ArrayList<Sonido> = ArrayList()
    private var todos: ArrayList<Sonido> = ArrayList()
    private var pregunta: ArrayList<Sonido> = ArrayList()
    private var on: Boolean = false
    private lateinit var asc: Array<Int>
     private var cont : Int = 1
    private  var puntaje: Int = 0
    private var dificulty: Int = 1

    fun setDificulty(intd: Int){
        dificulty = intd
    }
    fun getDificulty(): Int = dificulty


    fun setCONT(c:Int ) {
        cont = c
    }
    fun getCont() = cont
    fun getPunta() = puntaje
    fun setPunta(c:Int ) {
        puntaje = c
    }



    fun seton(respuestaSonido: Boolean) {
        on=respuestaSonido
    }
    fun geton() = on
    fun setasc(respuestaSonido: Array<Int>) {
        asc=respuestaSonido
    }
    fun getasc() = asc

    fun setRespuesta(respuestaSonido: Sonido) {
        respuesta =respuestaSonido
    }
    fun setActual(respuestaSonido: ArrayList<Sonido> ) {
        actual =respuestaSonido
    }
    fun setTodos(respuestaSonido: ArrayList<Sonido> ) {
        todos =respuestaSonido
    }
    fun getRespuesta(): Sonido? {
        if (::respuesta.isInitialized){
            return respuesta
        }else{
            return null
        }
    }

    fun getActual(): ArrayList<Sonido>  = actual

    fun getTodos(): ArrayList<Sonido> = todos

    fun getPregunta(): ArrayList<Sonido> = pregunta

    fun setPregunta(respuestaSonido: ArrayList<Sonido> ) {
        pregunta =respuestaSonido
    }


}