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

        viewModel.searchResults.observe(viewLifecycleOwner) { searchResults ->
            adapter.updateFoodItems(searchResults)

        }




        searchFoodBtn.setOnClickListener {
            val query = foodInput.text.toString()
            if (!TextUtils.isEmpty(query)) {
                // perform search query
                viewModel.loadSearchResults(query)
                Log.d("FoodSearchFragment", "Perform search query for ${foodInput.text}")
                Log.d("FoodSearchFragment", "viewModel ${viewModel.searchResults.value}")
//                dummyResults.add(viewModel.searchResults.value)
                //adapter.updateFoodItems(dummyResults)
                foodDataListRV.scrollToPosition(0)
            }


        }


    }
}
