package org.techtown.phtoiet

import androidx.lifecycle.LiveData
import androidx.room.*


//데이터베이스에 접근하는 함수 (insert,update,delete,...)를  제공
@Dao
interface DAO {

    @Query("SELECT * FROM tb_profiles")
    fun getAll(): List<Profiles>

    @Insert
    fun insertAll(vararg contacts: Profiles)

    @Delete
    fun delete(profiles: Profiles)

}