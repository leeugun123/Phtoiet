package org.techtown.phtoiet.MealViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import org.techtown.MealDao
import org.techtown.MealDatabase
import org.techtown.phtoiet.Meal


///MMVM패턴의 Model(Repository)을 하는 역할
class MealReposityory (application: Application){

    private val mealDao : MealDao
    private val mealList: LiveData<List<Meal>>

    init {
        var db = MealDatabase.getInstance(application)
        mealDao = db!!.MealDao()
        mealList = db.MealDao().getAll()
    }//view model의 초기 설정

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