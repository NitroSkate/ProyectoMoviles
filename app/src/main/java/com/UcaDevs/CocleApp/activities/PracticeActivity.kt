package com.UcaDevs.CocleApp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.UcaDevs.CocleApp.R
import com.UcaDevs.CocleApp.Room.Viewmodel.PreguntaViewmodel
import com.UcaDevs.CocleApp.fragmentos_practica.Practica1Fragment

class PracticeActivity : AppCompatActivity(), Practica1Fragment.OnFragmentInteractionListener {

    //private lateinit var p1 : Preguntas1Fragmento

    private lateinit var preguntaViewmodel: PreguntaViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice)
        preguntaViewmodel = ViewModelProviders.of(this).get(PreguntaViewmodel::class.java)
        //preguntaViewmodel.retrievePreguntas()
        if (savedInstanceState == null) {
            initfragment(1,true)
        }

    }

    fun initfragment(id: Int,start: Boolean){
        var frag = Practica1Fragment.newInstance(id,start)
        changefragment(R.id.pr_content, frag)
    }

    override fun onNextQuestion(string: String, id: Int,start: Boolean) {
        if(string == "next") {
            //Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()
            initfragment(id,start)
        }
        else if(string == "si"){
            initfragment(1,start)
        } else if (string == "no"){
            val intent = Intent(this@PracticeActivity, ScoreActivity::class.java)
            intent.putExtra("AMD", 100)
            startActivity(intent)
        }
    }


    fun changefragment(id: Int, frag: Fragment){
        supportFragmentManager.beginTransaction().replace(id,frag).commit()
    }
}