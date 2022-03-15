package org.techtown.phtoiet

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class ProfileAdapter(private val profileList: List<Profiles>) : RecyclerView.Adapter<ProfilesViewHolder>(){

    override fun getItemCount(): Int {
        return profileList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilesViewHolder {
        //plug로 연결될 것들이 무엇인가?
        //붙이는 작업
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler,parent,false)
        //context란 activity에서 담고 있는 모든 정보들
        return ProfilesViewHolder(view)
    }

    override fun onBindViewHolder(holder:ProfilesViewHolder, position: Int) {

        val item = profileList[position]

        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it,position)
        }
        holder.apply {
            bind(item)
        }
    }

    interface OnItemClickListener{
        fun onClick(v: View,position: Int)
    }
    private lateinit var itemClickListener : OnItemClickListener

    fun setItemClickListener(itemClickListener: OnItemClickListener){
        this.itemClickListener = itemClickListener
    }

}
//상속 개념
//profiles 클래스를 Arraylist 형태로 만들어 리스트화 시킴

