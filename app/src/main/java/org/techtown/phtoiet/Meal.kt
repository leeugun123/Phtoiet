package org.techtown.phtoiet

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity와 object의 차이
//object는 개체를 포함한 더 큰 의미를 가짐,
//대상에 대한 정보 뿐만 아니라 동작,기능,절차등을 포함
@Entity//어노테이션
class Meal (
    var mealName : String,//음식이름

    var mealTime : String,//음식 먹은 시간

    var calories : String//음식 칼로리
){//@데이터를 설명하는 데이터
    @PrimaryKey(autoGenerate = true)//자동키 값 설정,유일하 키값
    var id: Int = 0
}// 생성자를 이용해서, 초기값을 주어도 된다.