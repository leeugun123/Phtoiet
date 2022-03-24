package org.techtown.phtoiet

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.techtown.phtoiet.databinding.RecyclerViewTestBinding

class MealAdapter(listener: OnItemClick) : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    private val imageLoader: ImageLoader
        get() {
            TODO()
        }
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


        private val meal_name = itemView.findViewById<TextView>(R.id.meal_name)
        private val meal_time = itemView.findViewById<TextView>(R.id.time)
        private val meal_calories = itemView.findViewById<TextView>(R.id.calories)

        fun bind(meal: Meal){


            imageLoader.imageLoadWithFile(meal.mealPhoto,binding.foodImage)

            meal_name.text = meal.mealName
            meal_time.text = meal.mealTime
            meal_calories.text = meal.calories

            binding.deleteButton.setOnClickListener{
                mCallback.deleteMeal(meal)
            }//클릭시 데이터 삭제

        }
    }
}