package org.techtown.phtoiet

import android.app.Activity
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
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.activity_menu.*
import org.techtown.phtoiet.databinding.ActivityMenuBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Menu_Activity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMenuBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMenuBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        val nickname = findViewById<TextView>(R.id.nickname)//닉네임

        UserApiClient.instance.me { user, error ->
            "${user?.kakaoAccount?.profile?.nickname}".also { nickname.text = it }
        }//카카오에서 닉네임 불러오기

        val profile = findViewById<ImageView>(R.id.profile)

        UserApiClient.instance.me { user, error ->
            Glide.with(profile).load(user?.kakaoAccount?.profile?.profileImageUrl).circleCrop().into(profile)
        }//카카오톡 프로필 이미지 가져오기

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_DATE
        val formatted = current.format(formatter)

        Today_date.setText(formatted)//오늘 날짜 가져오기

        //네비게이션을 담는 호스트
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host) as NavHostFragment

        //네비게이션 컨트롤러

        val navController = navHostFragment.navController

        //바텀 네비게이션 뷰와 네비게이션을 묶어준다
        NavigationUI.setupWithNavController(mBinding.myBottomNav,navController)

    }




}



