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
import com.example.team42fitness.R
import com.example.team42fitness.api.FoodDataSearchService
import com.example.team42fitness.data.foodData.FoodItem
import com.example.team42fitness.data.foodData.Nutrients

class FoodSearchFragment: Fragment(R.layout.fragment_food_search) {
    private val foodDataSearchService = FoodDataSearchService.create()
    private val viewModel: FoodSearchViewModel by viewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val foodInput = view.findViewById<EditText>(R.id.et_food_search_entry)
        val searchFoodBtn = view.findViewById<Button>(R.id.btn_search_food_data)

        val foodDataListRV: RecyclerView = view.findViewById(R.id.rv_food_data)
        foodDataListRV.layoutManager = LinearLayoutManager(requireContext())
        foodDataListRV.setHasFixedSize(true)

        val adapter = NutritionAdapter()
        foodDataListRV.adapter = adapter

        viewModel.searchResults.observe(viewLifecycleOwner) {searchResults ->
            adapter.updateFoodItems(searchResults)

        }




        searchFoodBtn.setOnClickListener{
            val query = foodInput.text.toString()
            if (!TextUtils.isEmpty(query)){
                // perform search query
                viewModel.loadSearchResults(query)
                Log.d("FoodSearchFragment", "Perform search query for ${foodInput.text}")
                Log.d("FoodSearchFragment", "viewModel ${viewModel.searchResults.value}")
//                dummyResults.add(viewModel.searchResults.value)
                adapter.updateFoodItems(dummyResults)
                foodDataListRV.scrollToPosition(0)
            }


        }


    }

//    private suspend fun doFoodSearch(q: String) {
//        foodDataSearchService.loadFoodResults(q, "fzLMJqmkWeci3bkjhONuhFt4M9ZjGc6rwj1jCBfQ")
//    }
    val dummyResults = mutableListOf(
    FoodItem(1, "desc 1", listOf(Nutrients("Calories", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
    FoodItem(2, "desc 2", listOf(Nutrients("Calories", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
    FoodItem(3, "desc 3", listOf(Nutrients("Calories", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
    FoodItem(4, "desc 4", listOf(Nutrients("Calories", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
    FoodItem(5, "desc 5", listOf(Nutrients("Calories", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
    FoodItem(6, "desc 6", listOf(Nutrients("Calories", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
    FoodItem(7, "desc 7", listOf(Nutrients("Calories", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
    FoodItem(8, "desc 8", listOf(Nutrients("Calories", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
    FoodItem(9, "desc 9",  listOf(Nutrients("Calories", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
    FoodItem(10, "desc 10", listOf(Nutrients("Calories", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
    FoodItem(12, "desc 11", listOf(Nutrients("Calories", "KCAL", 200f), Nutrients("Protein", "G", 20f))),
    )



}