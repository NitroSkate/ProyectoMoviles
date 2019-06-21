package com.polillas.cocleapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.polillas.cocleapp.R
import kotlinx.android.synthetic.main.activity_terapist.*

class TerapistActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terapist)
        auth = FirebaseAuth.getInstance()

        terapista_log.text = "Bienvenido/a " + auth.currentUser?.email

        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@TerapistActivity, AccountActivity::class.java))
        }
    }
}
