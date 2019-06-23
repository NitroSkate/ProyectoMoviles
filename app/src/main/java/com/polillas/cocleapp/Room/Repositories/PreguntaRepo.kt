package com.polillas.cocleapp.Room.Repositories

import com.polillas.cocleapp.Room.DAO.PreguntaDAO
import com.polillas.cocleapp.Room.DAO.SonidoDAO
import com.polillas.cocleapp.Room.Entities.Pregunta
import com.polillas.cocleapp.Room.Entities.ResponsePreg
import com.polillas.cocleapp.Room.Retrofit.retrofit
import kotlinx.coroutines.Deferred
import retrofit2.Response

class PreguntaRepo(private val preguntaDao : PreguntaDAO/*, private val sonidoDao : SonidoDAO, private val preguntaRepo: PreguntaRepo*/){

    fun responsePregunta() : Deferred<Response<List<ResponsePreg>>> {
        return retrofit.getPreguntas().obtainQuestions()
    }

    fun getPregunta(id : String) : Pregunta = preguntaDao.getOnePregunta(id)

    fun getAllPreguntas(): List<Pregunta> = preguntaDao.getPreguntas()



}