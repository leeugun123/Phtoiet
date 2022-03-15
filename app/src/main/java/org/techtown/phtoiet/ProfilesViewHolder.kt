package org.techtown.phtoiet

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dialog_custom.view.*
import kotlinx.android.synthetic.main.item_recycler.view.*

class ProfilesViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    var view: View = v;

    fun bind(item: Profiles){
        view.food_picture.text = item.Food_Image
        view.eat_food.text = item.food_name
        view.calories.text = item.calories
        view.time.text = item.time
    }
}