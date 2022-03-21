package org.techtown.phtoiet

import android.app.Application
import androidx.lifecycle.LiveData

class MealReposityory (application: Application){

    private val mealDatabase = MealDatabase.getInstance(application)!!
    private val mealDao : MealDao = mealDatabase.MealDao()
    private val meals : LiveData<List<Meal>> = mealDao.getAll()


    fun getAll() : LiveData<List<Meal>>{
        return meals
    }

    fun insert(meal : Meal){
        try{
            val thread = Thread(Runnable {
                mealDao.insert(meal)
            })
            thread.start()
        }catch (e: Exception){e.printStackTrace()}
    }

    fun delete(meal : Meal){
        try {
            val thread = Thread(Runnable {
                mealDao.delete(meal)
            })
            thread.start()
        }catch (e: java.lang.Exception){e.printStackTrace()}
    }

    fun update(meal : Meal){

        try {
            val thread = Thread(Runnable {
                mealDao.update(meal)
            })
            thread.start()
        }catch (e: java.lang.Exception){e.printStackTrace()}
    }

}