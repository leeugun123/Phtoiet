package org.techtown.phtoiet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class MealAdapter(val mealItemClick: (Meal) -> Unit)
    : RecyclerView.Adapter<MealAdapter.ViewHolder>(){

    var meal : List<Meal> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_test,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(meal[position])
    }

    override fun getItemCount(): Int {
        return meal.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val id = itemView.findViewById<TextView>(R.id.number)
        private val name = itemView.findViewById<TextView>(R.id.meal_name)
        private val time = itemView.findViewById<TextView>(R.id.time)
        private val calories = itemView.findViewById<TextView>(R.id.calories)

        fun bind(meal : Meal){

            id.text = meal.id.toString()
            name.text = meal.mealName
            time.text = meal.mealTime
            calories.text = meal.calories


            itemView.setOnClickListener{
                mealItemClick(meal)
            }

        }

        fun setMeal(meal : List<Meal>){

        }//여기서 무언가 이상 다시한번 살펴보기
    }
}