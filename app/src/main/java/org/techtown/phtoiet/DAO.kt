package org.techtown.phtoiet

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DAO {

    //데이터 베이스 불러오기
    @Query("SELECT * FROM tb_entity")
    fun getAll(): List<Entity>

    //데이터 베이스 추가
    @Insert
    fun insertAll(vararg entity: Entity)

    @Delete
    fun delete(entity: Entity)
}