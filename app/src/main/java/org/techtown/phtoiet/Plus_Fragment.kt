package org.techtown.phtoiet

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.setPadding
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.dialog_custom.*
import kotlinx.android.synthetic.main.fragment_plus_.*
import java.lang.Exception
import java.util.jar.Manifest

import java.util.zip.Inflater

class Plus_Fragment : Fragment() {

    private val OPEN_GALLERY = 1
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

            val intent = Intent(activity,record_activity::class.java)
            startActivity(intent)
        }//추가를 눌렀을 경우,
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // 돌려받은 resultCode가 정상인지 체크
        if(resultCode == Activity.RESULT_OK){
            Reading_Database()
            Toast.makeText(activity,"값이 변경되었습니다.",Toast.LENGTH_SHORT).show()
        }
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
                        document["food_picture"] as String?,
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


}


