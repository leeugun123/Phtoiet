package org.techtown.phtoiet

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.kakao.sdk.user.UserApiClient
import org.techtown.phtoiet.databinding.ActivityMenuBinding

class Menu_Activity : AppCompatActivity() {

    lateinit var binding:ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_menu)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button1.setOnClickListener{
            setFragment1()
        }

        val nickname = findViewById<TextView>(R.id.nickname)//닉네임

        UserApiClient.instance.me { user, error ->
            "${user?.kakaoAccount?.profile?.nickname}".also { nickname.text = it }
        }//카카오에서 닉네임 불러오기


        val profile = findViewById<ImageView>(R.id.profile)

        UserApiClient.instance.me { user, error ->
            Glide.with(profile).load(user?.kakaoAccount?.profile?.profileImageUrl).circleCrop().into(profile)
        }//카카오톡 프로필 이미지 가져오기

        /*
        val kakao_logout_button = findViewById<Button>(R.id.logout_button) // 로그인 버튼

        kakao_logout_button.setOnClickListener {
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Toast.makeText(this, "로그아웃 실패 $error", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_SHORT).show()
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }

        val kakao_unlink_button = findViewById<Button>(R.id.out_button) // 로그인 버튼

        kakao_unlink_button.setOnClickListener {
            UserApiClient.instance.unlink { error ->
                if (error != null) {
                    Toast.makeText(this, "회원 탈퇴 실패 $error", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this, "회원 탈퇴 성공", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP))
                    finish()
                }
            }
        }
        */

    }

    fun setFragment1(){

        val transaction = supportFragmentManager.beginTransaction()
            .add(R.id.frame,Today_fragment())

        transaction.commit()

    }
}