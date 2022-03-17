package org.techtown.phtoiet.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import kotlinx.android.synthetic.main.fragment_friends.*
import org.techtown.phtoiet.AppDatabase
import org.techtown.phtoiet.Entity
import org.techtown.phtoiet.databinding.FragmentFriendsBinding
import org.techtown.phtoiet.databinding.FragmentHomeBinding
import java.util.*


//오늘의 일정 확인 하는 Fragment
class FriendsFragment : Fragment(){


    private var mBinding : FragmentFriendsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentFriendsBinding.inflate(inflater,container,false)

        mBinding = binding

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


}