package org.techtown.phtoiet

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.*

@Entity(tableName = "tb_profiles")
data class Profiles(

    @PrimaryKey(autoGenerate = true) val id:Long,
    val Food_Image: String?,
    val food_name : String?,
    val calories : String?,
    val time : String?
    //?는 꼭 붙여야한다.

)
