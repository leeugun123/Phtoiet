package org.techtown.phtoiet.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.phtoiet.databinding.FragmentFriendsBinding


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