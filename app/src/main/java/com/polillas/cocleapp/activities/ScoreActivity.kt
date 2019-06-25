package com.polillas.cocleapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.polillas.cocleapp.MainActivity
import com.polillas.cocleapp.R
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {
    var puntaje: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        var d = intent.extras
        puntaje = d.getInt("AMD")
        puntaje_tv.text = puntaje.toString()
        this.apply {
            lL_scoreaceptar.setOnClickListener {
                val intent = Intent(this@ScoreActivity, com.polillas.cocleapp.MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
