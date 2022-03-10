package org.techtown.phtoiet

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.setPadding
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.dialog_custom.*
import kotlinx.android.synthetic.main.fragment_plus_.*

import java.util.zip.Inflater

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
        //파이어베이스에서 정보를 가져와 recyclerView에 보여주기

        Reading_Database()//초반 접속했을 경우 데이터베이스 읽기


        floating.setOnClickListener {
            Writing_Database()
        }//추가를 눌렀을 경우,
    }

    fun Reading_Database(){

        list.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        list.adapter = adapter

        db.collection("Contacts")
            .get()
            .addOnSuccessListener { result ->
                itemList.clear()
                for (document in result) {
                    val item = Profiles(
                        document["Pictures"].toString() as String?,
                        document["food_name"].toString() as String?,
                        document["calories"].toString() as String?,
                        document["time"].toString() as String?
                    )
                    itemList.add(item)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->

            }
    }//데이터 읽어오기

    @SuppressLint("WrongConstant")
    fun Writing_Database(){

        var builder = AlertDialog.Builder(activity)

        builder.setTitle("식단 입력")
        builder.setIcon(R.mipmap.ic_launcher_round)

        var v1 = layoutInflater.inflate(R.layout.dialog_custom,null)
        builder.setView(v1)

        builder.setPositiveButton("확인"){ dialog,which->

            //Log.d("TAG","여기까지 올려나??")

            var dialog = dialog as AlertDialog

            val food_p : EditText? = dialog.findViewById<EditText>(R.id.food_picture)
            val food_n : EditText? = dialog.findViewById<EditText>(R.id.food_name)
            val food_c : EditText? = dialog.findViewById<EditText>(R.id.calories)
            val food_t : EditText? = dialog.findViewById<EditText>(R.id.time)

            var Profiles = Profiles("${food_p?.text}","${food_n?.text}","${food_c?.text}","${food_t?.text}")

            db.collection("Contacts")
                .add(Profiles)
                .addOnSuccessListener {
                    Toast.makeText(activity, "데이터가 추가되었습니다", Toast.LENGTH_LONG).show()
                    Reading_Database()
                }
                .addOnFailureListener{  exception ->
                    Toast.makeText(activity, "데이터가 추가실패", Toast.LENGTH_LONG).show()
                }

        }
        builder.setNegativeButton("취소",null)
        builder.show()



    }


}


