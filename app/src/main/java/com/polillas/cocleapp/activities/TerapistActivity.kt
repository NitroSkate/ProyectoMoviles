package com.polillas.cocleapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.polillas.cocleapp.R
import com.polillas.cocleapp.Room.Entities.Paciente
import kotlinx.android.synthetic.main.activity_terapist.*
import kotlinx.android.synthetic.main.login.view.*

class TerapistActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    private lateinit var db : FirebaseFirestore

    private lateinit var inflater : LayoutInflater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terapist)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        inflater = LayoutInflater.from(this)

        var popup = inflater.inflate(R.layout.login, null).apply{
            send_bt.text = "Agregar Paciente"
        }

        var popupview = PopupWindow(popup, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true)

        terapista_log.text = "Bienvenido/a " + auth.currentUser?.email



        add.setOnClickListener {
            popupview.showAtLocation(it, Gravity.CENTER,0,0)
            popup.apply {
                send_bt.setOnClickListener {
                    popupview.dismiss()
                    if(TextUtils.isEmpty(email.text) || TextUtils.isEmpty(password.text)){
                        Toast.makeText(it.context, "No se ha podido iniciar sesion", Toast.LENGTH_SHORT).show()
                    }else {
                        val paciente = Paciente(0,email.text.toString(),password.text.toString(),"",0)

                        db.collection("Pacient " + auth.currentUser?.email).document("0")
                            .set(paciente)
                            .addOnSuccessListener { Log.d("success", "DocumentSnapshot successfully written!") }
                            .addOnFailureListener { e -> Log.w("fail", "Error writing document", e) }
                    }
                }
            }
        }

        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@TerapistActivity, AccountActivity::class.java))
        }
    }
}
