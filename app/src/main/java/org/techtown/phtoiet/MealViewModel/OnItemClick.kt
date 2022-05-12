package org.techtown.phtoiet.MealViewModel

import org.techtown.phtoiet.Meal

interface OnItemClick {
    fun deleteMeal(meal : Meal)
}