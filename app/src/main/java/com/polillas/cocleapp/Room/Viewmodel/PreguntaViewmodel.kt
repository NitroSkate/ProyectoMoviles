package com.polillas.cocleapp.Room.Viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.polillas.cocleapp.Room.Repositories.PreguntaRepo
import com.polillas.cocleapp.Room.RoomDB.CoclearDatabase
import kotlinx.coroutines.launch

class PreguntaViewmodel(application: Application):AndroidViewModel(application){

    private var repoPre: PreguntaRepo?=null

    init{
        val preguntaDao = CoclearDatabase.getDatabase(getApplication()).preguntaDAO()
        repoPre = PreguntaRepo(preguntaDao)
    }

    fun retrievePreguntas() = viewModelScope.launch {
        val response = repoPre!!.responsePregunta().await()
        if(response.isSuccessful) with(response){
            this.body()?.let {
                it.forEach {
                    Log.d("response", it.idSonido.rutaSonido)
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


}