package org.techtown.phtoiet

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.activity_menu.*
import org.techtown.phtoiet.R.id.frame
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Menu_Activity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val nickname = findViewById<TextView>(R.id.nickname)//닉네임

        UserApiClient.instance.me { user, error ->
            "${user?.kakaoAccount?.profile?.nickname}".also { nickname.text = it }
        }//카카오에서 닉네임 불러오기

        val profile = findViewById<ImageView>(R.id.profile)

        UserApiClient.instance.me { user, error ->
            Glide.with(profile).load(user?.kakaoAccount?.profile?.profileImageUrl).circleCrop().into(profile)
        }//카카오톡 프로필 이미지 가져오기

        val Today_button = findViewById<Button>(R.id.button1)
        Today_button.setOnClickListener{
            Today_Fragment()
        }

        val Plus_button = findViewById<Button>(R.id.button2)
        Plus_button.setOnClickListener{
            Plus_fragment()
        }

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_DATE
        val formatted = current.format(formatter)
        Today_date.setText(formatted)//오늘 날짜 가져오기

    }

    fun Today_Fragment(){
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.frame,Today_fragment()).commit()
    }//오늘의 식단정보를 알려주는 메소드

    fun Plus_fragment(){
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.frame,Plus_Fragment()).commit()
    }


}



