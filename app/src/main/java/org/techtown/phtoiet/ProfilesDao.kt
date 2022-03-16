package org.techtown.phtoiet

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ProfilesDao {
    @Query("SELECT * FROM tb_profiles")
    fun getAll(): List<Profiles>

    @Insert
    fun insertAll(vararg profiles: Profiles)

    @Delete
    fun delete(profiles: Profiles)

}