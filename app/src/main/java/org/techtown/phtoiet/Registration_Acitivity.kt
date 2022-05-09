package org.techtown.phtoiet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.inflate
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.techtown.phtoiet.databinding.ActivityMainBinding.inflate
import org.techtown.phtoiet.databinding.ActivityMenuBinding.inflate
import org.techtown.phtoiet.databinding.ActivityRegistrationBinding
import org.techtown.phtoiet.databinding.AlertdialogEdittextBinding.inflate
import org.techtown.phtoiet.databinding.FragmentFriendsBinding

class Registration_Acitivity : AppCompatActivity() {

    private val model: MealViewModel by viewModels()
    private lateinit var adapter: MealAdapter
    private lateinit var mBinding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        mBinding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.ADDButton.setOnClickListener{

            val mealName = mBinding.eatFood.text.toString()
            val mealTime = mBinding.eatTime.text.toString()
            val calories = mBinding.calories.text.toString()

           lifecycleScope.launch(Dispatchers.IO){
               model.insert(Meal(mealName,mealTime,calories))
           }//코루틴으로 ViewModel 접근
           //main thread에서 접근하는 것이 아닌 코루틴을 이용하여 DB에 접근한다.(주의사항)

            finish()//액티비티 종료
      }




    }
}