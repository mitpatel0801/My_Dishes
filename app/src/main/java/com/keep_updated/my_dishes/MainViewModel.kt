package com.keep_updated.my_dishes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _dishList: MutableLiveData<MutableList<Dish>> = MutableLiveData()
    val dishList: LiveData<MutableList<Dish>>
        get() = _dishList

    init {
        _dishList.value = getAllDishes()
    }


    fun addDish(dish: Dish) {
        val oldList = _dishList.value!!
        oldList.add(dish)
        _dishList.value = oldList
    }

    fun deleteDish(dish: Dish) {
        val oldList = dishList.value!!
        oldList.remove(dish)
        _dishList.value = oldList
    }

    private fun getAllDishes(): MutableList<Dish> {
        return mutableListOf(
            Dish(
                "Sushi",
                "Sushi is Made of small pieces of raw fish that are wrapped in rice and seaweed."
            ),
            Dish(
                "Lasagna",
                "Lasagna is one of the oldest pasta but has become popular only in the present times."
            ),
            Dish(
                " Fried chicken",
                "Crunchy on the outside and juicy within, fried chicken, as the name suggests, is a dish consisting of chicken pieces deeply fried, giving it a crisp coating."
            ),
            Dish(
                "Masala Chai",
                "If there's one Indian drink that goes with everything (except dessert) it's masala chai."
            ),
            Dish(
                "Dosa",
                "To put it in simple terms, dosa is a type of pancake made from fermented rice batter."
            ),
            Dish(
                "Hamburger",
                "Hamburgers are often served with lettuce, loads of cheese, tomato, pickles and whatnot."
            ),
            Dish(
                "Pizza",
                "This Italian Dish consists of a usually round, wheat base dough that is garnished with tomatoes, cheese, and often various other ingredients baked at a high temperature, traditionally in a wood-fired oven."
            ),

            )
    }

}