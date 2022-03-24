package org.techtown.phtoiet

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MealAdapter(listener: OnItemClick) : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    private val mCallback = listener
    private val items = ArrayList<Meal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_test,parent,false)
        return MealViewHolder(view)
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

    inner class MealViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        private val meal_name = itemView.findViewById<TextView>(R.id.meal_name)
        private val meal_time = itemView.findViewById<TextView>(R.id.time)
        private val meal_calories = itemView.findViewById<TextView>(R.id.calories)

        fun bind(meal: Meal){
            meal_name.text = meal.mealName
            meal_time.text = meal.mealTime
            meal_calories.text = meal.calories
        }
    }
}