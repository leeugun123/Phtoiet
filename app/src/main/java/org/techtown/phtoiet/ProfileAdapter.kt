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

class ProfileAdapter(profileList1: Context, val profileList: ArrayList<Profiles>) : RecyclerView.Adapter<ProfileAdapter.FoodViewholder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.FoodViewholder {
        //plug로 연결될 것들이 무엇인가?
        //붙이는 작업

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler,parent,false)
        //context란 activity에서 담고 있는 모든 정보들
        return FoodViewholder(view)
    }

    override fun getItemCount(): Int {
        return profileList.size //profileList에 대한 갯수
    }

    override fun onBindViewHolder(holder: ProfileAdapter.FoodViewholder, position: Int) {
               holder.Picture.setImageResource(profileList.get(position).Picture)
               holder.foodName.text = profileList.get(position).food_name
               holder.calories.text = profileList.get(position).calories.toString()//double -> 문자열로 변환
               holder.time.text = profileList.get(position).time

    }//모든 데이터들을 매치해주는 곳

    class FoodViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val Picture = itemView.findViewById<ImageView>(R.id.food_picture) // 음식 사진
        val foodName = itemView.findViewById<TextView>(R.id.eat_food) //음식 종류
        val calories = itemView.findViewById<TextView>(R.id.calories) // 먹은 칼로리
        val time = itemView.findViewById<TextView>(R.id.time) // 먹은 시간

    }

}
//상속 개념
//profiles 클래스를 Arraylist 형태로 만들어 리스트화 시킴

