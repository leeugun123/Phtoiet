package org.techtown.phtoiet

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.phtoiet.databinding.RecyclerViewTestBinding

class MealAdapter(listener: OnItemClick) : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    private val mCallback = listener
    private val items = ArrayList<Meal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {

        val binding =  RecyclerViewTestBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MealViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(items[position])
    }


    fun setList(meal : List<Meal>){
        items.clear()
        items.addAll(meal)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MealViewHolder(private val binding : RecyclerViewTestBinding):RecyclerView.ViewHolder(binding.root){

        private val meal_photo = itemView.findViewById<ImageView>(R.id.food_image)//음식 사진
        private val meal_name = itemView.findViewById<TextView>(R.id.meal_name)//음식 이름
        private val meal_time = itemView.findViewById<TextView>(R.id.time)//먹은 시간
        private val meal_calories = itemView.findViewById<TextView>(R.id.calories)//칼로리

        fun bind(meal: Meal){

            Glide.with(binding.root).load(meal.mealPhoto).into(meal_photo)
            //사진 붙여넣기 잘 안된다..

            meal_name.text = meal.mealName
            meal_time.text = meal.mealTime
            meal_calories.text = meal.calories

            binding.deleteButton.setOnClickListener{
                mCallback.deleteMeal(meal)
            }//클릭시 데이터 삭제

        }
    }
}