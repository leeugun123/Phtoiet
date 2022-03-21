package org.techtown.phtoiet

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal")
class Meal (

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    @ColumnInfo(name = "mealName")
    var mealName : String = "",

    @ColumnInfo(name = "mealTime")
    var mealTime : String = "",

    @ColumnInfo(name = "mealCalories")
    var calories : String = ""
)// 생성자를 이용해서, 초기값을 주어도 된다.