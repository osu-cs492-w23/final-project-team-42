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
import com.example.team42fitness.data.foodData.Nutrients

class NutritionFragment: Fragment(R.layout.fragment_food_data) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addFoodBtn = view.findViewById<Button>(R.id.btn_add_food_data)

        val foodDateListRV: RecyclerView = view.findViewById(R.id.rv_food_data)
        foodDateListRV.layoutManager = LinearLayoutManager(requireContext())
        foodDateListRV.setHasFixedSize(true)

        val adapter = NutritionAdapter2()
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


    val dummyResults = mutableListOf(
        FoodItem(1, "name 1","Pepsi co.",
                            listOf(
                                Nutrients("Carbohydrate, by difference", "G", 200f),
                                Nutrients("Protein", "G", 20f),
                                Nutrients("Energy", "KCAL", 200f))),
        FoodItem(2, "name 2","Pepsi co.",
                            listOf(
                                Nutrients("Carbohydrate, by difference", "G", 200f),
                                Nutrients("Protein", "G", 20f),
                                Nutrients("Energy", "KCAL", 200f))),
        FoodItem(3, "name 3", "Pepsi co.",
                            listOf(
                                Nutrients("Carbohydrate, by difference", "G", 200f),
                                Nutrients("Protein", "G", 20f),
                                Nutrients("Energy", "KCAL", 200f))),
        FoodItem(4, "name 4", "Pepsi co.",
                            listOf(
                                Nutrients("Carbohydrate, by difference", "G", 200f),
                                Nutrients("Protein", "G", 20f),
                                Nutrients("Energy", "KCAL", 200f))),
        FoodItem(5, "name 5", "Pepsi co.",
                            listOf(
                                Nutrients("Carbohydrate, by difference", "G", 200f),
                                Nutrients("Protein", "G", 20f),
                                Nutrients("Energy", "KCAL", 200f))),
        FoodItem(6, "name 6", "Pepsi co.",
                            listOf(
                                Nutrients("Carbohydrate, by difference", "G", 200f),
                                Nutrients("Protein", "G", 20f),
                                Nutrients("Energy", "KCAL", 200f))),
        FoodItem(7, "name 7", "Pepsi co.",
                            listOf(
                                Nutrients("Carbohydrate, by difference", "G", 200f),
                                Nutrients("Protein", "G", 20f),
                                Nutrients("Energy", "KCAL", 200f))),
        FoodItem(8, "name 8", "Pepsi co.",
                            listOf(
                                Nutrients("Carbohydrate, by difference", "G", 200f),
                                Nutrients("Protein", "G", 20f),
                                Nutrients("Energy", "KCAL", 200f))),
        FoodItem(9, "name 9", "Pepsi co.",
                            listOf(
                                Nutrients("Carbohydrate, by difference", "G", 200f),
                                Nutrients("Protein", "G", 20f),
                                Nutrients("Energy", "KCAL", 200f))),
        FoodItem(10, "name 10", "Pepsi co.",
                            listOf(
                                Nutrients("Carbohydrate, by difference", "G", 200f),
                                Nutrients("Protein", "G", 20f),
                                Nutrients("Energy", "KCAL", 200f))),
        FoodItem(12, "name 11", "Pepsi co.",
                            listOf(
                                Nutrients("Carbohydrate, by difference", "G", 200f),
                                Nutrients("Protein", "G", 20f),
                                Nutrients("Energy", "KCAL", 200f))),
    )
}
