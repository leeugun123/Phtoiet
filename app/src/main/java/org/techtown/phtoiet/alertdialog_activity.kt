package org.techtown.phtoiet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.techtown.phtoiet.databinding.AlertdialogEdittextBinding
import org.techtown.phtoiet.databinding.FragmentFriendsBinding

class alertdialog_activity : AppCompatActivity() {

    private lateinit var binding : AlertdialogEdittextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.dialogButton.setOnClickListener{

            Log.d("TAG","버튼 눌렸다.!!")
        }

    }
}