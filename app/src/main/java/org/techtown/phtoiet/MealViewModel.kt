package org.techtown.phtoiet

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class MealViewModel (application: Application) : AndroidViewModel(application){

    private val reposityory = MealReposityory(application)
    private val meal = reposityory.getAll()

    fun getAll() : LiveData<List<Meal>>{
        return this.meal
    }

    fun insert(meal : Meal){
        reposityory.insert(meal)
    }

    fun delte(meal : Meal){
        reposityory.delete(meal)
    }

    fun update(meal : Meal){
        reposityory.update(meal)
    }
}