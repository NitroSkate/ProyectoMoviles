package com.polillas.cocleapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.polillas.cocleapp.R
import com.polillas.cocleapp.Room.Viewmodel.PreguntaViewmodel
import com.polillas.cocleapp.fragmentos_practica.Practica1Fragment
import com.polillas.cocleapp.fragmentos_preguntas.Preguntas1Fragmento
import com.polillas.cocleapp.fragmentos_preguntas.Preguntas2Fragmento

class PracticeActivity : AppCompatActivity(), Practica1Fragment.OnFragmentInteractionListener {

    //private lateinit var p1 : Preguntas1Fragmento

    private lateinit var preguntaViewmodel: PreguntaViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice)
        preguntaViewmodel = ViewModelProviders.of(this).get(PreguntaViewmodel::class.java)
        //preguntaViewmodel.retrievePreguntas()
        initfragment(1)
    }

    fun initfragment(id: Int){
        var frag = Practica1Fragment.newInstance(id)
        changefragment(R.id.pr_content, frag)
    }

    override fun onNextQuestion(string: String, id: Int) {
        if(string == "next") {
            //Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()
            initfragment(id)
        }
        else{
            val intent = Intent(this@PracticeActivity, ScoreActivity::class.java)
            startActivity(intent)
        }
    }


    fun changefragment(id: Int, frag: Fragment){
        supportFragmentManager.beginTransaction().replace(id,frag).commit()
    }
}