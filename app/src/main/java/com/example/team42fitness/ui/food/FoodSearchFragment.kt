package com.example.team42fitness.ui.food

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.R
import com.example.team42fitness.data.foodData.FoodItem
import com.example.team42fitness.data.foodData.Nutrients

class FoodSearchFragment: Fragment(R.layout.fragment_food_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val foodInput = view.findViewById<EditText>(R.id.et_food_search_entry)
        val searchFoodBtn = view.findViewById<Button>(R.id.btn_search_food_data)

        val foodDataListRV: RecyclerView = view.findViewById(R.id.rv_food_data)
        foodDataListRV.layoutManager = LinearLayoutManager(requireContext())
        foodDataListRV.setHasFixedSize(true)

        val adapter = NutritionAdapter()
        foodDataListRV.adapter = adapter




        searchFoodBtn.setOnClickListener{
            val query = foodInput.text.toString()
            if (!TextUtils.isEmpty(query)){
                // perform search query
                Log.d("FoodSearchFragment", "Perform search query for ${foodInput.text}")
                adapter.updateFoodItems(dummyResults)
                foodDataListRV.scrollToPosition(0)
            }


        }


    }

    private val dummyResults = listOf(
        FoodItem(1, "desc 1", Nutrients("Calories", "KCAL", 200f)),
        FoodItem(2, "desc 2", Nutrients("Calories", "KCAL", 200f)),
        FoodItem(3, "desc 3", Nutrients("Calories", "KCAL", 200f)),
        FoodItem(4, "desc 4", Nutrients("Calories", "KCAL", 200f)),
        FoodItem(5, "desc 5", Nutrients("Calories", "KCAL", 200f)),
        FoodItem(6, "desc 6", Nutrients("Calories", "KCAL", 200f)),
        FoodItem(7, "desc 7", Nutrients("Calories", "KCAL", 200f)),
        FoodItem(8, "desc 8", Nutrients("Calories", "KCAL", 200f)),
        FoodItem(9, "desc 9", Nutrients("Calories", "KCAL", 200f)),
        FoodItem(10, "desc 10", Nutrients("Calories", "KCAL", 200f)),
        FoodItem(12, "desc 11", Nutrients("Calories", "KCAL", 200f)),



        )


}