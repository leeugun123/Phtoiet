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

    var TAG = "Plus_Fragment"
    var db : AppDatabase? = null
    var Profiles_List = mutableListOf<Profiles>()

override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_plus_, container, false)
        return rootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //DB 초기화 과정
        db = activity?.let { AppDatabase.getInstance(it) }

        //이전에 저장한 내용 모두 불러와서 추가하기
        var savedProfiles = db!!.ProfilesDao().getAll()
        if(savedProfiles.isNotEmpty()){
            Profiles_List.addAll(savedProfiles)
        }


        floating.setOnClickListener {
            val intent = Intent(activity,record_activity::class.java)
            startActivity(intent)
        }//추가를 눌렀을 경우,


    }

}


