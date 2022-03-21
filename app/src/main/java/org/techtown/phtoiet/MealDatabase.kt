package org.techtown.phtoiet

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Meal::class], version = 1)
abstract class MealDatabase() : RoomDatabase(){

    abstract fun MealDao() : MealDao

    companion object{
        private var INSTANCE : MealDatabase? = null

        fun getInstance(context: Context) : MealDatabase?{
            if(INSTANCE == null){
                synchronized(MealDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        MealDatabase::class.java,"meal")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}
