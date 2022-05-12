package org.techtown.phtoiet.Fragment.Search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.phtoiet.databinding.FragmentMusicBinding

class MusicFragment : Fragment(){

    private lateinit var binding : FragmentMusicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onDestroyView() {

        super.onDestroyView()
    }


}