package com.example.implantecoclear.fragmentos_preguntas

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.implantecoclear.R
import kotlinx.android.synthetic.main.fragment_preguntas2_fragmento.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Preguntas2Fragmento.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Preguntas2Fragmento.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class Preguntas2Fragmento : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_preguntas2_fragmento, container, false).apply {
            one2.setOnClickListener {
                listener?.onNextQuestion("finish", 0)
            }
        }
        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onNextQuestion(string: String, id:Int)
    }

    companion object {
        @JvmStatic
        fun newInstance(): Preguntas2Fragmento{
            var frag = Preguntas2Fragmento()
            return frag
        }
    }
}
