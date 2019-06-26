package com.polillas.cocleapp.activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.polillas.cocleapp.R
import com.polillas.cocleapp.Room.Entities.Paciente
import com.polillas.cocleapp.Room.Entities.Pacientes
import com.polillas.cocleapp.Room.Entities.Puntaje
import com.polillas.cocleapp.recycler.ListPatientAdapter
import kotlinx.android.synthetic.main.activity_terapist.*
import kotlinx.android.synthetic.main.addpatient.view.*
import kotlinx.android.synthetic.main.fragment_new_account.view.*
import kotlinx.android.synthetic.main.login.view.*
import java.util.*
import kotlin.collections.ArrayList

class TerapistActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    private lateinit var db : FirebaseFirestore

    private lateinit var inflater : LayoutInflater

    private lateinit var listPatientAdapter: ListPatientAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terapist)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        inflater = LayoutInflater.from(this)

        var popup = inflater.inflate(R.layout.addpatient, null)
        var popupview =
            PopupWindow(popup, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true)

        if(auth.currentUser?.displayName == null){
            terapista_log.text = "Bienvenido/a "+ auth.currentUser?.email
        } else{
            terapista_log.text = "Bienvenido/a " + auth.currentUser?.displayName
        }


        retrievedocuments()


        add.setOnClickListener {
            popupview.showAtLocation(it, Gravity.CENTER,0,0)
            popup.apply {
                add_patient.setOnClickListener {
                    popupview.dismiss()
                    if(TextUtils.isEmpty(name_patient.text) || TextUtils.isEmpty(lname_patient.text)){
                        Toast.makeText(it.context, "No se ha podido iniciar sesion", Toast.LENGTH_SHORT).show()
                    }else {
                        val id = UUID.randomUUID().toString()
                        val list = mutableListOf<Puntaje>()
                        list.add(Puntaje("0", "0"))
                        val paciente = Pacientes(id,name_patient.text.toString(),lname_patient.text.toString(),birth_patient.dayOfMonth.toString()
                            + "/" + birth_patient.month.toString() + "/" + birth_patient.year.toString(),lvl_patient.value.toString(),list)

                        db.collection("Pacient " + auth.currentUser?.email).document(id)
                            .set(paciente)
                            .addOnSuccessListener { Log.d("success", "DocumentSnapshot successfully written!") }
                            .addOnFailureListener { e -> Log.w("fail", "Error writing document", e) }
                    }
                }
            }
            retrievedocuments()
        }

        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@TerapistActivity, AccountActivity::class.java))
        }
    }

    fun retrievedocuments(){
        val lista = ArrayList<String?>()
        val listp = mutableListOf<Puntaje>()
        val list = mutableListOf<Pacientes>()
        listp.add(Puntaje("0", "0"))
        db.collection("Pacient " + auth.currentUser?.email)
            .get()
            .addOnSuccessListener { results ->
                for (document in results) {
                    Log.d("abr", document.getString("nombre"))
                    val paciente = Pacientes(document.id, document.getString("nombre"), document.getString("apellido"), document.getString("fechaIngreso"), document.getString("nivel"), listp)
                    //lista.add(document.getString("nombre"))
                    list.add(paciente)
                    Toast.makeText(this, document.id, Toast.LENGTH_SHORT).show()
                }
                initrecycler(list)
                //val listadap = ArrayAdapter(this, R.layout.listapp, lista)
                //patient_list.adapter = listadap
            }
            .addOnFailureListener { exc ->
                Log.d("fail", "Murio", exc)
            }


    }

    fun initrecycler(lista : List<Pacientes>){
        val linearlayoutmanager = LinearLayoutManager(this)
        listPatientAdapter = ListPatientAdapter(lista, {item: Pacientes -> onClickPatient(item)}, auth, db)

        recycler.adapter = listPatientAdapter
        listPatientAdapter.notifyDataSetChanged()
        recycler.apply{
            setHasFixedSize(true)
            layoutManager = linearlayoutmanager
        }
    }

    private fun onClickPatient(item: Pacientes){
        Log.d("objeto", item.nombre)
        /*var popup = inflater.inflate(R.layout.fragment_new_account, null)
        var popupview = PopupWindow(popup, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true)

        popupview.showAtLocation(, Gravity.CENTER,0,0)
        popup.apply {*/
    }
}
