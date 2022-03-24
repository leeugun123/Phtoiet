package org.techtown.phtoiet

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//ROOM DataBase의 역할을 해줌
@Database(entities = [Meal::class], version = 1, exportSchema = false)
abstract class MealDatabase() : RoomDatabase(){
    //RoomDatabase() 상속

    abstract fun MealDao() : MealDao

    companion object{
        private var INSTANCE : MealDatabase? = null

        @Synchronized
        fun getInstance(context: Context) : MealDatabase?{
            if(INSTANCE == null){
                synchronized(MealDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        MealDatabase::class.java,"meal-database")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}
