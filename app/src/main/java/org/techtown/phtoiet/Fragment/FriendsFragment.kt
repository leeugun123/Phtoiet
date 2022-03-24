package org.techtown.phtoiet.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.techtown.phtoiet.*
import org.techtown.phtoiet.databinding.ActivityMenuBinding
import org.techtown.phtoiet.databinding.FragmentFriendsBinding


//오늘의 일정 확인 하는 Fragment
class FriendsFragment : Fragment() , OnItemClick {

        private val model: MealViewModel by viewModels()
        private lateinit var adapter: MealAdapter
        private lateinit var mBinding : FragmentFriendsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = FragmentFriendsBinding.inflate(layoutInflater)

        initRecyclerView()//리사이클러뷰 초기화


        model.getAll().observe(this, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })//observer를 붙여서 데이터가 변경되면, adapter에게 넘기고 새로고침


        mBinding?.mPlusButton?.setOnClickListener{

            Toast.makeText(activity, "플래그먼트에서도 Toast가 됩니다", Toast.LENGTH_LONG).show()

            lifecycleScope.launch(Dispatchers.IO){
                model.insert(Meal("eeeee","eeeee","eeeeee"))
            }//메인 쓰레드에서 실행하면 안되므로 코루틴 사용??

        }//데이터 추가

    }

    private fun initRecyclerView(){
        mBinding?.mRecyclerView?.layoutManager = LinearLayoutManager(activity)
        adapter = MealAdapter(this)
        mBinding?.mRecyclerView?.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?) : View?{
        val binding = FragmentFriendsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun deleteMeal(meal: Meal) {
        lifecycleScope.launch(Dispatchers.IO){
            model.delete(meal)
        }
    }


}