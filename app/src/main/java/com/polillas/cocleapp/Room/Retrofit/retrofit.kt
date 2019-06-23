package com.polillas.cocleapp.Room.Retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.polillas.cocleapp.Room.Entities.Pregunta
import com.polillas.cocleapp.Room.Entities.ResponsePreg
import com.polillas.cocleapp.constants.AppConstants
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface retrofit{

    @GET("/api/pregunta/")
    fun obtainQuestions(): Deferred<Response<List<ResponsePreg>>>

    companion object{
        fun getPreguntas(): retrofit{
            return Retrofit.Builder().baseUrl(AppConstants.BASE_URL).addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory()).build().create(retrofit::class.java)
        }
    }
}