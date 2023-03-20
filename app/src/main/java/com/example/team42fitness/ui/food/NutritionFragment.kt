package com.example.team42fitness.ui.food

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.R
import com.example.team42fitness.data.foodData.FoodItem
import com.example.team42fitness.data.foodData.Nutrient

class NutritionFragment: Fragment(R.layout.fragment_food_data) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val addFoodBtn = view.findViewById<Button>(R.id.btn_add_food_data) //change name here

        val foodDateListRV: RecyclerView = view.findViewById(R.id.rv_food_data)
        foodDateListRV.layoutManager = LinearLayoutManager(requireContext())
        foodDateListRV.setHasFixedSize(true)

        val adapter = NutritionAdapter2() // create new adapter
        foodDateListRV.adapter = adapter


        adapter.updateFoodItems(dummyResults)

        addFoodBtn.setOnClickListener{
            onFoodSearchClick()
        }

    }


    private fun onFoodSearchClick(){
        val directions = NutritionFragmentDirections.navigateToSearchFood()
        findNavController().navigate(directions)
    }

    private val dummyResults = listOf(
        FoodItem(1, "desc 1", listOf(Nutrient("Calories", "KCAL", 200f))),
        FoodItem(2, "desc 2", listOf(Nutrient("Calories", "KCAL", 200f))),
        FoodItem(3, "desc 3", listOf(Nutrient("Calories", "KCAL", 200f))),
        FoodItem(4, "desc 4", listOf(Nutrient("Calories", "KCAL", 200f))),
        FoodItem(5, "desc 5", listOf(Nutrient("Calories", "KCAL", 200f))),
        FoodItem(6, "desc 6", listOf(Nutrient("Calories", "KCAL", 200f))),
        FoodItem(7, "desc 7", listOf(Nutrient("Calories", "KCAL", 200f))),
        FoodItem(8, "desc 8", listOf(Nutrient("Calories", "KCAL", 200f))),
        FoodItem(9, "desc 9", listOf(Nutrient("Calories", "KCAL", 200f))),
        FoodItem(10, "desc 10", listOf(Nutrient("Calories", "KCAL", 200f))),
        FoodItem(12, "desc 11", listOf(Nutrient("Calories", "KCAL", 200f))),
        )
}