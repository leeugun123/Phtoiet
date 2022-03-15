package org.techtown.phtoiet

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.dialog_custom.*

class record_activity : AppCompatActivity() {

    var TAG = "record_activity"
    var db : AppDatabase? = null
    var Profiles_List = mutableListOf<Profiles>()
    var adapter = ProfileAdapter(Profiles_List)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_custom)

        back_button.setOnClickListener{
            finish()
        }//뒤로가기 버튼

        save_button.setOnClickListener{
            Writing_Database()
            val intent = Intent()
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }

    @SuppressLint("WrongConstant")
    fun Writing_Database(){

        //값이 입력 안됨 .. 형식적인 문제 한번 다시 고칠것
        val food_picture = Editext_food_picture.getText().toString()
        val food_name = Editext_food_name.getText().toString()
        val calories = Editext_calories.getText().toString()
        val time = Editext_time.getText().toString()

        val profiles = Profiles(0,food_picture,food_name,calories,time)
        //Profiles 생성
        db?.ProfilesDao()?.insertAll(profiles)
        //DB에 추가
        Profiles_List.add(profiles)
         //리스트 추가
        adapter.notifyDataSetChanged()
        //리스트뷰 갱신
    }
}