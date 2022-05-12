package org.techtown.phtoiet.Fragment.List

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.techtown.MealAdapter
import org.techtown.phtoiet.*
import org.techtown.phtoiet.MealViewModel.MealViewModel
import org.techtown.phtoiet.MealViewModel.OnItemClick
import org.techtown.phtoiet.databinding.FragmentFriendsBinding


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