package org.techtown.phtoiet

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MealDao {

    @Query("SELECT * FROM meal ORDER BY id ASC")
    fun getAll() : LiveData<List<Meal>>
    //어디든 변경이 생기면 업데이트 가능

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(meal : Meal)

    @Delete
    fun delete(meal : Meal)

    @Update
    fun update(meal : Meal)
}