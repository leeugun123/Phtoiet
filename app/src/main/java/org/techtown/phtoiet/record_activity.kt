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

    val db = FirebaseFirestore.getInstance()

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
        }//파이어베이스에 데이터 쓰기


    }


    @SuppressLint("WrongConstant")
    fun Writing_Database(){

        val food_picture = EditText(this)
        val food_name = EditText(this)
        val calories = EditText(this)
        val time = EditText(this)

        val data = hashMapOf(
            "food_picture" to food_picture.text.toString(),
            "food_name" to food_name.text.toString(),
            "calories" to calories.text.toString(),
            "time" to time.text.toString()
        )

        //일단 Firebase에 저장은 됨.
        db.collection("Contacts")
            .add(data)
            .addOnSuccessListener{
                Toast.makeText(this,"값이 성공적으로 저장",Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener{ exception ->
                Toast.makeText(this,"실패",Toast.LENGTH_LONG).show()
            }
    }
}