package org.techtown.phtoiet

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_menu.*
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
        //파이어베이스에서 정보를 가져와 recyclerView에 보여주기

        Reading_Database()

        floating.setOnClickListener {
            Writing_Database()
            Reading_Database()
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
                        document["Pictures"] as String?,
                        document["calories"] as String?,
                        document["food_name"] as String?,
                        document["time"] as String?
                    )
                    itemList.add(item)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->

            }
    }

    fun Writing_Database(){

        val builder = AlertDialog.Builder(activity)

        val Pictures = TextView(activity)
        Pictures.text = "음식사진"

        val Calories = TextView(activity)
        Calories.text = "음식 칼로리"

        val Food_Name = TextView(activity)
        Food_Name.text = "음식 이름"

        val time = TextView(activity)
        time.text = "시간"

        val et_Pictures = EditText(activity)
        et_Pictures.isSingleLine = true
        val et_Calories = EditText(activity)
        et_Calories.isSingleLine = true
        val et_Food_Name = EditText(activity)
        et_Food_Name.isSingleLine = true
        val et_time = EditText(activity)
        et_time.isSingleLine = true

        val mLayout = LinearLayout(activity)
        // mLayout.orientation = LinearLayout.VERTICAL
        mLayout.setPadding(16)
        mLayout.addView(Pictures)
        mLayout.addView(et_Pictures)
        mLayout.addView(Calories)
        mLayout.addView(et_Calories)
        mLayout.addView(Food_Name)
        mLayout.addView(et_Food_Name)
        mLayout.addView(time)
        mLayout.addView(et_time)

        builder.setTitle("데이터 추가")
        builder.setPositiveButton("추가"){dialog, which ->

            val data = hashMapOf(
                "Pictures" to et_Pictures.toString(),
                "calories" to et_Calories.toString(),
                "food_name" to et_Food_Name.toString(),
                "time" to et_time.toString()
            )

            db.collection("Contacts")
                .add(data)
                .addOnSuccessListener {
                    Toast.makeText(activity, "데이터가 추가되었습니다", Toast.LENGTH_LONG).show()
                    Reading_Database()
                }
                .addOnFailureListener{  exception ->

                }

        }
        builder.setNegativeButton("취소"){dialog,which->

        }
        builder.show()
    }


}