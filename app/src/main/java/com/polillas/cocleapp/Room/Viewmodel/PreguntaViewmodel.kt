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

class PreguntaViewmodel(application: Application):AndroidViewModel(application){

    private var repoPre: PreguntaRepo?=null

    init{
        val preguntaDao = CoclearDatabase.getDatabase(getApplication()).preguntaDAO()
        val sonidoDao = CoclearDatabase.getDatabase(getApplication()).sonidoDAO()
        repoPre = PreguntaRepo(preguntaDao, sonidoDao)
    }

    fun retrievePreguntas() = viewModelScope.launch {
        val response = repoPre!!.responsePregunta().await()
        if(response.isSuccessful) with(response){
            this.body()?.let {
                var cont = 1
                it.forEach {
                    Log.d("response", it.idSonido.rutaSonido)
                    val preguntares = Pregunta(cont.toString(), it.nivel)
                    val sonidores = Sonido(cont.toString(), it.idSonido.rutaSonido, it.idSonido.rutaImagen)
                    this@PreguntaViewmodel.insertQuestion(preguntares)
                    this@PreguntaViewmodel.insertSound(sonidores)
                    cont++
                }
            }
        } else with(response){
            when(this.code()){
                404-> {
                    Toast.makeText(getApplication(), "Se murio", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun insertQuestion(pregunta: Pregunta?) = viewModelScope.launch (Dispatchers.IO){
        repoPre!!.insertPregunta(pregunta)
    }

    fun insertSound(sonido: Sonido?) = viewModelScope.launch (Dispatchers.IO){
        repoPre!!.insertSonido(sonido)
    }

    fun getAllPreguntas(): LiveData<List<Pregunta>> = repoPre!!.getAllPreguntas()

    fun getAllSonidos(): LiveData<List<Sonido>> = repoPre!!.getSonidos()




}