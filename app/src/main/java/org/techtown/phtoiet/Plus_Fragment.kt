package org.techtown.phtoiet

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
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_plus_.*
import org.techtown.phtoiet.databinding.ActivityMainBinding

class Plus_Fragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var mainActivity: MainActivity
    //private lateinit var binding : ActivityMainBinding

    val db = FirebaseFirestore.getInstance()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity) mainActivity = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_plus_, container, false)


        var foodList = ArrayList<Profiles>()//food의 ArrayList


        /*
        foodList.add(Profiles(R.drawable.noddle,"짜장면","803.4","12시 30분"))
        foodList.add(Profiles(R.drawable.cutlet,"돈가스","800.3","13시 20분"))
        */

        recyclerView = rootView.findViewById(R.id.list)as RecyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext())



            db.collection("Contacts")//작업할 컬렉션
                .get()  //문서 가져오기
                .addOnSuccessListener {result ->
                    //성공할경우
                    foodList.clear()
                        for(document in result){ val item = Profiles(document["img"] as String,document["food_name"] as String,document["calories"] as String, document["time"] as String)
                        foodList.add(item)
                    }
                }
                .addOnFailureListener{
                    //실패할경우
                    Toast.makeText(getActivity(),"Toast Message",Toast.LENGTH_SHORT).show();

                 }



        recyclerView.adapter =
            ProfileAdapter(requireContext(),foodList)


        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


}