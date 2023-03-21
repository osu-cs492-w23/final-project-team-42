package com.example.team42fitness.ui.food

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.FOODDATA_CENTRAL_APPID
import com.example.team42fitness.R
import com.example.team42fitness.data.foodData.FoodItem
import com.example.team42fitness.data.foodData.Nutrient

class FoodSearchFragment: Fragment(R.layout.fragment_food_search) {

    private val viewModel: FoodDataSearchViewModel by viewModels()

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
                viewModel.loadSearchResults(query, FOODDATA_CENTRAL_APPID)
                val search = viewModel.searchResults
                adapter.updateFoodItems(dummyResults)
                foodDataListRV.scrollToPosition(0)
            }


        }


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