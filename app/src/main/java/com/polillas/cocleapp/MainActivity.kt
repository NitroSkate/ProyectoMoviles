package com.polillas.cocleapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.polillas.cocleapp.activities.AccountActivity
import com.polillas.cocleapp.activities.ExerciseActivity
import com.polillas.cocleapp.fragmentos.AccountFragment
import com.polillas.cocleapp.fragmentos.ConfigFragment
import com.polillas.cocleapp.fragmentos.MenuFragment
import com.polillas.cocleapp.fragmentos.ModoFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ModoFragment.OnFragmentInteractionListener, MenuFragment.OnFragmentInteractionListener, ConfigFragment.OnFragmentInteractionListener {


    private lateinit var modo : ModoFragment

    private lateinit var menu : MenuFragment

    private var opc = String()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState != null){
            initfragment(opc)
        }
        else {
            opc = "menu"
            initfragment(opc)
        }
    }


    override fun onOpcion() {
        var intent = Intent(this@MainActivity, ExerciseActivity::class.java)
        startActivity(intent)
    }

    override fun onClickButton(string: String) {
        if(string == "terapista"){
            var intent = Intent(this@MainActivity, AccountActivity::class.java)
            startActivity(intent)
        }
        else {
            initfragment(string)
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
        supportFragmentManager.beginTransaction().replace(id,frag).commit()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putString("olv", opc)
    }
}
