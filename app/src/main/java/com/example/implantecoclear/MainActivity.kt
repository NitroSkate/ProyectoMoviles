package com.example.implantecoclear

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import com.example.implantecoclear.activities.ExerciseActivity
import com.example.implantecoclear.fragmentos.MenuFragment
import com.example.implantecoclear.fragmentos.ModoFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ModoFragment.OnFragmentInteractionListener, MenuFragment.OnFragmentInteractionListener {



    private lateinit var modo : ModoFragment

    private lateinit var menu : MenuFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initfragment("menu")
    }

    override fun onOpcion() {
        var intent = Intent(this@MainActivity, ExerciseActivity::class.java)
        startActivity(intent)
    }

    override fun onClickButton() {
        initfragment("mode")
    }

    fun initfragment(clave : String){
        if(clave == "mode"){
            modo = ModoFragment.newInstance()
            changefragment(R.id.content, modo)
        }
        if(clave == "menu"){
            menu = MenuFragment.newInstance()
            changefragment(R.id.content, menu)
        }

    }

    fun changefragment(id: Int, frag: Fragment){
        supportFragmentManager.beginTransaction().replace(id,frag).commit()
    }
}
