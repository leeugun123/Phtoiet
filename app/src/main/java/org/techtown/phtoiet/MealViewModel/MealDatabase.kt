package org.techtown

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.techtown.MealDao
import org.techtown.phtoiet.Meal


//ROOM DataBase의 역할을 해줌         //사진 속성 삭제로 인해 version 2 -> version 3로 변경

@Database(entities = [Meal::class], version = 6, exportSchema = false)
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
