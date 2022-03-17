package org.techtown.phtoiet

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entity::class], version =1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun Dao() : DAO

    companion object{
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase?{
            if(instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database-entity"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }

    }

}