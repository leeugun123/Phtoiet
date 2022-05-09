package org.techtown.phtoiet.Fragment

import android.app.Activity.RESULT_OK
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.techtown.phtoiet.*
import org.techtown.phtoiet.databinding.FragmentFriendsBinding
import java.io.File


class FriendsFragment : Fragment(), OnItemClick {

    private val model: MealViewModel by viewModels()
    private lateinit var adapter: MealAdapter
    private lateinit var binding : FragmentFriendsBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = FragmentFriendsBinding.inflate(layoutInflater)
        //데이터 바인딩

        initRecyclerView()//리사이클러뷰 초기화

        model.getAll().observe(this, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })//observer를 붙여서 데이터가 변경되면, adapter에게 넘기고 새로고침

        binding.mPlusButton.setOnClickListener{


            activity.let {
                val Intent = Intent(context,Registration_Acitivity::class.java)
                startActivity(Intent)
            }



        }//데이터 추가





    }



    private fun initRecyclerView(){

        adapter = MealAdapter(this)
        binding.mRecyclerView.adapter = adapter

        val dividerItemDecoration =
            DividerItemDecoration(binding.mRecyclerView.context,LinearLayoutManager(activity).orientation)
        binding.mRecyclerView.addItemDecoration(dividerItemDecoration)

    }


    override fun onDestroyView() {

        super.onDestroyView()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View?{
        return binding.root
    }

    override fun deleteMeal(meal: Meal) {
        lifecycleScope.launch(Dispatchers.IO){
            model.delete(meal)
        }
    }

}