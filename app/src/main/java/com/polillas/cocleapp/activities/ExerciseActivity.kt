package com.polillas.cocleapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.polillas.cocleapp.R
import com.polillas.cocleapp.Room.Entities.Puntaje
import com.polillas.cocleapp.Room.Viewmodel.PreguntaViewmodel
import com.polillas.cocleapp.fragmentos_preguntas.Preguntas1Fragmento


class ExerciseActivity : AppCompatActivity(), Preguntas1Fragmento.OnFragmentInteractionListener{

    //private lateinit var p1 : Preguntas1Fragmento

    private lateinit var preguntaViewmodel: PreguntaViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        preguntaViewmodel = ViewModelProviders.of(this).get(PreguntaViewmodel::class.java)
        //preguntaViewmodel.retrievePreguntas()
        initfragment(1,0)
    }

    fun initfragment(id: Int,puntaje: Int){
        /*when(id){
            1 -> {
                var frag = Preguntas2Fragmento.newInstance()
                changefragment(R.id.ex_content, frag)
            }*/

        var frag = Preguntas1Fragmento.newInstance(id,puntaje)
        changefragment(R.id.ex_content, frag)
    }


    override fun onNextQuestion(string: String, id: Int,puntaje: Int) {
        Toast.makeText(this,puntaje.toString(),Toast.LENGTH_SHORT).show()
        if(string == "next") {
            //Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()
            initfragment(id,puntaje)
        }
        else{
            val intent = Intent(this@ExerciseActivity, ScoreActivity::class.java)
            intent.putExtra("AMD",puntaje)
            startActivity(intent)
        }
    }


    fun changefragment(id: Int, frag: Fragment){
        supportFragmentManager.beginTransaction().replace(id,frag).commit()
    }
}
