package com.polillas.cocleapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.polillas.cocleapp.R
import kotlinx.android.synthetic.main.fragment_account.*

/*
TODO Pantalla de seleccion de dificultad que sera util para definir cuantas opciones se mostraran al usuario en la pantalla
TODO del cuestionario de sonidos
 */

class DificultyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_account)
        lL_principiante.setOnClickListener {
            startActivity(Intent(this@DificultyActivity, ExerciseActivity::class.java).putExtra("dificultad", 2))
        }
        lL_intermedio.setOnClickListener {
            startActivity(Intent(this@DificultyActivity, ExerciseActivity::class.java).putExtra("dificultad", 3))
        }
        lL_avanzado.setOnClickListener {
            startActivity(Intent(this@DificultyActivity, ExerciseActivity::class.java).putExtra("dificultad", 4))
        }
    }
}
