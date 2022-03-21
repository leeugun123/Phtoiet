package org.techtown.phtoiet

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Meal (
    var mealName : String = "",

    var mealTime : String = "",

    var calories : String = ""
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}// 생성자를 이용해서, 초기값을 주어도 된다.