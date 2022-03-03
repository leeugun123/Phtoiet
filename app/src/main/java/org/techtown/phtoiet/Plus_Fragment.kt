package org.techtown.phtoiet

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_plus_.*
import org.techtown.phtoiet.databinding.ActivityMainBinding

class Plus_Fragment : Fragment() {

    val db = FirebaseFirestore.getInstance()
    val itemList = arrayListOf<Profiles>()
    val adapter = ProfileAdapter(itemList)

override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_plus_, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        list.adapter = adapter

        db.collection("Contacts")
            .get()
            .addOnSuccessListener { result->
                itemList.clear()
                for(document in result){
                    val item = Profiles(document["Pictures"] as String?,document["calories"] as String?,document["food_name"] as String?,document["time"] as String?)
                    itemList.add(item)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener{exception ->

            }

    }


}