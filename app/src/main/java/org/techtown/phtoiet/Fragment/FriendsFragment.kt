package org.techtown.phtoiet.Fragment

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Environment.DIRECTORY_PICTURES
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.android.synthetic.main.recycler_view_test.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.techtown.phtoiet.*
import org.techtown.phtoiet.databinding.ActivityMenuBinding
import org.techtown.phtoiet.databinding.AlertdialogEdittextBinding
import org.techtown.phtoiet.databinding.FragmentFriendsBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


//오늘의 일정 확인 하는 Fragment
class FriendsFragment : Fragment() , OnItemClick {

    private val model: MealViewModel by viewModels()
    private lateinit var adapter: MealAdapter
    private lateinit var binding : FragmentFriendsBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = FragmentFriendsBinding.inflate(layoutInflater)
        //데이터 바인딩

        //Log.d("TAG","버튼 눌렸다.!!")

        initRecyclerView()//리사이클러뷰 초기화


        model.getAll().observe(this, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })//observer를 붙여서 데이터가 변경되면, adapter에게 넘기고 새로고침

        binding.mPlusButton.setOnClickListener{

            val builder = AlertDialog.Builder(activity)
            val builderItem = AlertdialogEdittextBinding.inflate(layoutInflater)

            val name_editText = builderItem.fodName
            val time_editText = builderItem.fodTime
            val cal_editText = builderItem.fodCal

            var fd_name = ""
            var fd_time = ""
            var fd_cal = ""

            with(builder){
                setView(builderItem.root)

                setPositiveButton("등록"){

                        dialogInterface: DialogInterface, i: Int ->

                    fd_name = name_editText.text.toString()
                    fd_time = time_editText.text.toString()
                    fd_cal = cal_editText.text.toString()

                    lifecycleScope.launch(Dispatchers.IO){

                        //delay(400)
                        model.insert(Meal(fd_name,fd_time,fd_cal+" Kcal"))

                    }
                }
                    .setNegativeButton("취소"){
                            dialogInterface: DialogInterface, i: Int ->
                    }
                    .show()
            }





            //메인 쓰레드에서 접근하면 오류가 날 수 있다... 반드시 코루틴 사용

        //editext로 내용을 받아 입력 구현하기

        }//데이터 추가

    }

    private fun initRecyclerView(){

        adapter = MealAdapter(this)
        binding.mRecyclerView.adapter = adapter

    }

    override fun onDestroyView() {

        super.onDestroyView()

    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?) : View?{
        return binding?.root
    }

    override fun deleteMeal(meal: Meal) {
        lifecycleScope.launch(Dispatchers.IO){
            model.delete(meal)
        }
    }



}