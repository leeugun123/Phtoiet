package org.techtown.phtoiet

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_entity")
data class Entity(
    @PrimaryKey(autoGenerate = true) val id:Int,
    var Name: String,
    var Number: String
)