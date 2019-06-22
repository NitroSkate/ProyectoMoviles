package com.polillas.cocleapp.fragmentos

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.AudioManager.STREAM_MUSIC
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.polillas.cocleapp.R
import kotlinx.android.synthetic.main.fragment_modo.view.*
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [modo.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [modo.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ModoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_modo, container, false)
        /*view.desafio.setOnClickListener{
            listener?.onOpcion()
        }*/

        //var mediaplayer : MediaPlayer = MediaPlayer.create(this.context, R.raw.pwtb)
        view.lL_Practica.setOnClickListener {
            try {

                val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
                    setDataSource("https://projecto-moviles.herokuapp.com/upload/sounds/undefined_1561234022587_34.mp3")
                    prepare()
                    start()
                }
            } catch (e : IOException){
                e.printStackTrace()
            }

            /*if(mediaplayer.isPlaying()){
                mediaplayer.pause()
            }*/
            //else {
            //mediaplayer.start()
            //}
        }
        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener") as Throwable
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onOpcion()
    }

    companion object {
        fun newInstance() : ModoFragment{
            var fragmento = ModoFragment()
            return fragmento
        }
    }
}
