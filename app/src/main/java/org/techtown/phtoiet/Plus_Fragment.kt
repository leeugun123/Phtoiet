package org.techtown.phtoiet

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_plus_.*

class Plus_Fragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity) mainActivity = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_plus_, container, false)

        val foodList = arrayListOf(
            Profiles(R.drawable.noddle,"짜장면",803.4,"12시 30분"),
            Profiles(R.drawable.cutlet,"돈가스",560.3,"13시 20분")
        )

        recyclerView = rootView.findViewById(R.id.list)as RecyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext())
        recyclerView.adapter =
            ProfileAdapter(requireContext(),foodList)


        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        floating.setOnClickListener{

        }

    }


}