package com.polillas.cocleapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.polillas.cocleapp.R
import com.polillas.cocleapp.Room.Viewmodel.PreguntaViewmodel
import com.polillas.cocleapp.fragmentos_preguntas.Preguntas1Fragmento


class ExerciseActivity : AppCompatActivity(), Preguntas1Fragmento.OnFragmentInteractionListener{

    //private lateinit var p1 : Preguntas1Fragmento

    private lateinit var preguntaViewmodel: PreguntaViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        preguntaViewmodel = ViewModelProviders.of(this).get(PreguntaViewmodel::class.java)

        if (savedInstanceState == null) {
            var intent = intent.extras
            var lvl = intent.getInt("dificultad")
            initfragment(1,0,true,lvl)
        }
    }

    fun initfragment(id: Int,puntaje: Int,start: Boolean,dificultad: Int){

        var frag = Preguntas1Fragmento.newInstance(id,puntaje,start,dificultad)
        changefragment(R.id.ex_content, frag)
    }


    override fun onNextQuestion(string: String, id: Int,puntaje: Int,start: Boolean,dificultad:Int) {

        
        if(string == "next") {
            //Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()
            initfragment(id,puntaje,start,dificultad)
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
