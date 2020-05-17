package com.waichee.randommeal01.detail


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.waichee.randommeal01.detail.MealApiStatus.DONE
import com.waichee.randommeal01.detail.MealApiStatus.ERROR
import com.waichee.randommeal01.detail.MealApiStatus.LOADING
import com.waichee.randommeal01.network.Ingredient
import com.waichee.randommeal01.network.Meal
import com.waichee.randommeal01.network.Meals
import com.waichee.randommeal01.network.MealsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


enum class MealApiStatus { LOADING, ERROR, DONE }

class DetailViewModel : ViewModel() {


    private val _status = MutableLiveData<MealApiStatus>()
    val status: LiveData<MealApiStatus>
        get() = _status


    private val _meal = MutableLiveData<Meal?>()
    val meal: LiveData<Meal?>
        get() = _meal


    private val _ingredients = MutableLiveData<List<Ingredient?>>()
    val ingredients: LiveData<List<Ingredient?>>
        get() = _ingredients


    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        getRandomMeal()
    }

    fun getRandomMeal() {
        coroutineScope.launch {
            val getRandomMealDeferred = MealsApi.retrofitService.getRandomMeal()
            try {
                _status.value = LOADING
                val Result = getRandomMealDeferred.await()
                _status.value = DONE
                _meal.value = Result.meals[0]

                _ingredients.value = listOf(
                    Ingredient(Result.meals[0].strIngredient1, Result.meals[0].strMeasure1),
                    Ingredient(Result.meals[0].strIngredient2, Result.meals[0].strMeasure2),
                    Ingredient(Result.meals[0].strIngredient3, Result.meals[0].strMeasure3),
                    Ingredient(Result.meals[0].strIngredient4, Result.meals[0].strMeasure4),
                    Ingredient(Result.meals[0].strIngredient5, Result.meals[0].strMeasure5),
                    Ingredient(Result.meals[0].strIngredient6, Result.meals[0].strMeasure6))

                Log.i("DetailViewModel", "${Result.meals}")

            } catch (e: Exception) {
                _status.value = ERROR
                Log.i("DetailViewModel", "$e.message")

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}