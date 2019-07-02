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

//TODO en esta clase se crea el viewmodel de las preguntas

class PreguntaViewmodel(application: Application):AndroidViewModel(application){

    //TODO instancia de repositorio de preguntas
    private var repoPre: PreguntaRepo?=null

    //TODO se inicializan variables con los DAO de la base de datos room
    init{
        val preguntaDao = CoclearDatabase.getDatabase(getApplication()).preguntaDAO()
        val sonidoDao = CoclearDatabase.getDatabase(getApplication()).sonidoDAO()
        repoPre = PreguntaRepo(preguntaDao, sonidoDao)
    }

    //TODO en esta funcion se trata de obtener datos de la API en caso esto falle se enviara un mensaje de error
    //Caso contrario se limpiara la base y se almacenaran los nuevos datos
    fun retrievePreguntas() = viewModelScope.launch {
        this@PreguntaViewmodel.nukeQ()
        this@PreguntaViewmodel.nukeS()
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
                    Toast.makeText(getApplication(), "ERROR: archivo no encontrado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //TODO esta funcion inserta las preguntas dentro de su respectiva tabla
    fun insertQuestion(pregunta: Pregunta?) = viewModelScope.launch (Dispatchers.IO){
        repoPre!!.insertPregunta(pregunta)
    }
    //TODO esta funcion inserta los recursos de la pregunta correspondiente dentro de su respectiva tabla
    fun insertSound(sonido: Sonido?) = viewModelScope.launch (Dispatchers.IO){
        repoPre!!.insertSonido(sonido)
    }

    //TODO las funciones siguientes se encargan de obtener los datos de la base de datos para devolverlos al momento de ser llamada
    fun getAllPreguntas(): LiveData<List<Pregunta>> = repoPre!!.getAllPreguntas()

    fun getAllSonidos(): LiveData<List<Sonido>> = repoPre!!.getSonidos()

    //TODO las funciones siguientes se encargan de limpiar la base de datos
    fun nukeQ() = viewModelScope.launch (Dispatchers.IO){
        repoPre!!.nukeQ()
    }

    fun nukeS() = viewModelScope.launch (Dispatchers.IO){
        repoPre!!.nukeS()
    }


}