package com.polillas.cocleapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.polillas.cocleapp.MainActivity
import com.polillas.cocleapp.R
import com.polillas.cocleapp.fragmentos.AccountFragment

/*
TODO Actividad encargada de manejar las acciones de los botones del fragmento account y el encargado de iniciar el fragmento
TODO account.
 */

class AccountActivity : AppCompatActivity(), AccountFragment.OnFragmentInteractionListener {


    private lateinit var auth: FirebaseAuth

    private lateinit var auth2: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        auth = FirebaseAuth.getInstance()
        initfragment()
    }

    override fun onLogin(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("success", "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("fail", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

            }
    }

    override fun onRegister(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("success", "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("fail", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Error. No se pudo crear al usuario",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }


    fun initfragment(){
        var acc = AccountFragment.newInstance()
        changefragment(R.id.account_layout, acc)
    }

    fun changefragment(id: Int, frag: Fragment){
        supportFragmentManager.beginTransaction().replace(id,frag).commit()
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@AccountActivity, MainActivity::class.java))
    }

    private fun updateUI(user: FirebaseUser?){
        if(user != null){
            startActivity(Intent(this@AccountActivity, TerapistActivity::class.java))
        }
    }
}
