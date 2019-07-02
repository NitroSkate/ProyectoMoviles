package com.polillas.cocleapp.Room.Repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.polillas.cocleapp.Room.DAO.PreguntaDAO
import com.polillas.cocleapp.Room.DAO.SonidoDAO
import com.polillas.cocleapp.Room.Entities.Pregunta
import com.polillas.cocleapp.Room.Entities.ResponsePreg
import com.polillas.cocleapp.Room.Entities.Sonido
import com.polillas.cocleapp.Room.Retrofit.retrofit
import kotlinx.coroutines.Deferred
import retrofit2.Response

/*TODO esta clase se encarga de contener todos los metodos correspondientes a las preguntas */

class PreguntaRepo(private val preguntaDao : PreguntaDAO, private val sonidoDao : SonidoDAO/*, private val preguntaRepo: PreguntaRepo*/){

    //TODO Esta funcion se encarga de hacer una llamada a retrofit y obtener los datos de la pregunta
    fun responsePregunta() : Deferred<Response<List<ResponsePreg>>> {
        return retrofit.getPreguntas().obtainQuestions()
    }

    //TODO Los siguientes metodos se encargan de obtener datos directamente desde la base de datos de room
    fun getPregunta(id : String) : Pregunta = preguntaDao.getOnePregunta(id)

    fun getAllPreguntas(): LiveData<List<Pregunta>> = preguntaDao.getPreguntas()

    fun getSonidos(): LiveData<List<Sonido>> = sonidoDao.getAllSonidos()


    @WorkerThread
    suspend fun insertPregunta(pregunta : Pregunta?) = preguntaDao.insert(pregunta)

    @WorkerThread
    suspend fun insertSonido(sonido: Sonido?) = sonidoDao.insertSound(sonido)

    @WorkerThread
    suspend fun nukeQ()= preguntaDao.nukeQ()

    @WorkerThread
    suspend fun nukeS()= sonidoDao.nukeS()


}