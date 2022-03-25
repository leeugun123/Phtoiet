package org.techtown.phtoiet

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//ROOM DataBase의 역할을 해줌         //데이터 속성 추가로 인해 version 1 -> version 2로 변경
@Database(entities = [Meal::class], version = 2, exportSchema = false)
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
                        .fallbackToDestructiveMigration()//기존 데이터를 버리고 다음 버전으로 넘어감
                        .build()
                }
            }
            return INSTANCE
        }
    }
}
