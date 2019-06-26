package com.polillas.cocleapp.Room.Viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.polillas.cocleapp.Room.Entities.Pregunta
import com.polillas.cocleapp.Room.Entities.Sonido
import com.polillas.cocleapp.Room.Repositories.PreguntaRepo
import com.polillas.cocleapp.Room.RoomDB.CoclearDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(application: Application): AndroidViewModel(application){

    private lateinit var respuesta: Sonido
    private var actual: ArrayList<Sonido> = ArrayList()
    private var todos: ArrayList<Sonido> = ArrayList()
    private var pregunta: ArrayList<Sonido> = ArrayList()
    private var on: Boolean = false
    lateinit var asc: Array<Int>

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