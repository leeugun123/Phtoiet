package org.techtown.phtoiet.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.techtown.phtoiet.*


//오늘의 일정 확인 하는 Fragment
class FriendsFragment : Fragment() , OnItemClick {

    private val model: MealViewModel by viewModels()
    private lateinit var adapter: MealAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lm = LinearLayoutManager(activity)

        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = lm
        mRecyclerView.setHasFixedSize(true)

        model.getAll().observe(this, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })

        mPlusButton.setOnClickListener{
            lifecycleScope.launch(Dispatchers.IO){
                model.insert(Meal("eeeee","eeeee","eeeeee"))
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?) : View?{
        val view = inflater.inflate(R.layout.fragment_friends,null)
        return view
    }

    override fun deleteMeal(meal: Meal) {
        lifecycleScope.launch(Dispatchers.IO){
            model.delete(meal)
        }
    }


}