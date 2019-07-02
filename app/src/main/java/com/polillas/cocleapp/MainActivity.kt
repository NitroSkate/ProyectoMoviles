package com.polillas.cocleapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.polillas.cocleapp.Room.Viewmodel.PreguntaViewmodel
import com.polillas.cocleapp.activities.AccountActivity
import com.polillas.cocleapp.activities.DificultyActivity
import com.polillas.cocleapp.activities.PracticeActivity
import com.polillas.cocleapp.fragmentos.ConfigFragment
import com.polillas.cocleapp.fragmentos.MenuFragment
import com.polillas.cocleapp.fragmentos.ModoFragment

/*
    TODO actividad principal que es utilizado como punto de inicio de la aplicacion. Se manejan 3 fragmentos: menu y modo.
    TODO Se tiene una funcion que verifica si el dispositivo posee conexion a internet y una condicional que define si la informacion
    TODO ya llego al dispositivo (Toast que contiene espere la conexion). Todas las acciones de los botones de los fragmentos son manejados
    TODO por implementaciones de interfaz de cada fragmento
 */

class MainActivity : AppCompatActivity(), ModoFragment.OnFragmentInteractionListener, MenuFragment.OnFragmentInteractionListener, ConfigFragment.OnFragmentInteractionListener {


    private lateinit var modo : ModoFragment

    private lateinit var menu : MenuFragment

    private var opc = String()

    private lateinit var preguntaViewmodel: PreguntaViewmodel

    private var constant = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preguntaViewmodel = ViewModelProviders.of(this).get(PreguntaViewmodel::class.java)
        if(checkNetworkStatus()) {
            preguntaViewmodel.retrievePreguntas()
            if(savedInstanceState != null){
                initfragment(opc)
            }
            else {
                opc = "menu"
                initfragment(opc)
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        initfragment("menu")
    }


    override fun onOpcion(verify: Int, string: String) {
        if(checkNetworkStatus()) {
            if (verify > 0 && string == "exercise") {
                var intent = Intent(this@MainActivity, DificultyActivity::class.java)
                startActivity(intent)
            } else if (verify > 0 && string == "practice") {
                var intent = Intent(this@MainActivity, PracticeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Espere a la conexion", Toast.LENGTH_SHORT).show()
                initfragment("mode")
            }
        } else {
            Toast.makeText(this, "No hay internet, favor conectarse a un punto de red", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClickButton(string: String) {
        if(checkNetworkStatus()) {
            if (string == "terapista") {
                var intent = Intent(this@MainActivity, AccountActivity::class.java)
                startActivity(intent)
            } else {
                initfragment(string)
            }
        } else {
            Toast.makeText(this, "No hay internet, favor conectarse a un punto de red", Toast.LENGTH_SHORT).show()
        }
    }

    fun initfragment(clave : String){

        when (clave) {
            "mode" -> {
                modo = ModoFragment.newInstance()
                changefragment(R.id.content, modo)
            }
            "menu" -> {
                menu = MenuFragment.newInstance()
                changefragment(R.id.content, menu)
            }
            "config" ->{
                var config = ConfigFragment.newInstance()
                changefragment(R.id.content, config)
            }
        }
        opc = clave

    }

    fun changefragment(id: Int, frag: Fragment){
        supportFragmentManager.beginTransaction().replace(id,frag).addToBackStack("f_main").commit()
    }

    private fun checkNetworkStatus(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager){
            val networkInfo : NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putString("olv", opc)
    }
}
