package com.polillas.cocleapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.polillas.cocleapp.R
import com.polillas.cocleapp.fragmentos.AccountFragment

class AccountActivity : AppCompatActivity(), AccountFragment.OnFragmentInteractionListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        initfragment()
    }

    override fun onRegister(email: String, password: String) {

    }


    fun initfragment(){
        var acc = AccountFragment.newInstance()
        changefragment(R.id.account_layout, acc)
    }

    fun changefragment(id: Int, frag: Fragment){
        supportFragmentManager.beginTransaction().replace(id,frag).commit()
    }
}
