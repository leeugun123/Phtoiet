package org.techtown.phtoiet

import android.app.Application
import androidx.lifecycle.LiveData

class MealReposityory (application: Application){

    private val mealDao : MealDao
    private val mealList: LiveData<List<Meal>>

    init {
        var db = MealDatabase.getInstance(application)
        mealDao = db!!.MealDao()
        mealList = db.MealDao().getAll()
    }

    fun insert(meal : Meal){
        mealDao.insert((meal))
    }

    fun delete(meal : Meal){
        mealDao.delete(meal)
    }

    fun getAll(): LiveData<List<Meal>>{
        return mealDao.getAll()
    }

}