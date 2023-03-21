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

    val dummyResults = mutableListOf(
        FoodItem(1, "name 1", listOf(Nutrients("Carbohydrate, by difference", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
        FoodItem(2, "name 2", listOf(Nutrients("Carbohydrate, by difference", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
        FoodItem(3, "name 3", listOf(Nutrients("Carbohydrate, by difference", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
        FoodItem(4, "name 4", listOf(Nutrients("Carbohydrate, by difference", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
        FoodItem(5, "name 5", listOf(Nutrients("Carbohydrate, by difference", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
        FoodItem(6, "name 6", listOf(Nutrients("Carbohydrate, by difference", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
        FoodItem(7, "name 7", listOf(Nutrients("Carbohydrate, by difference", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
        FoodItem(8, "name 8", listOf(Nutrients("Carbohydrate, by difference", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
        FoodItem(9, "name 9",  listOf(Nutrients("Carbohydrate, by difference", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
        FoodItem(10, "name 10", listOf(Nutrients("Carbohydrate, by difference", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
        FoodItem(12, "name 11", listOf(Nutrients("Carbohydrate, by difference", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
    )
}