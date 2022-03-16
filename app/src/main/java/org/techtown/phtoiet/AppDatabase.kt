package org.techtown.phtoiet

import android.content.Context
import androidx.room.CoroutinesRoom
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Profiles::class], version = 1, exportSchema = false)//스키마 추출X
abstract class AppDatabase : RoomDatabase() {
    //RoomDatabase() 상속
    abstract fun ProfilesDao(): ProfilesDao

    companion object{

        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {

            if(instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database-Profiles"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }

}