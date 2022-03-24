package org.techtown.phtoiet

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class MealViewModel (application: Application) : AndroidViewModel(application){

    private val repository = MealReposityory(application)
    private val items = repository.getAll()

    fun getAll() : LiveData<List<Meal>>{
        return items
    }

    fun insert(meal : Meal){
        repository.insert(meal)
    }

    fun delete(meal : Meal){
        repository.delete(meal)
    }


}