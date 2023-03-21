package com.example.team42fitness.ui.food

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.R
import com.example.team42fitness.api.FoodDataSearchService
import com.example.team42fitness.data.foodData.FoodDataSearchRepository
import com.example.team42fitness.data.foodData.FoodItem
import com.example.team42fitness.data.foodData.LoadingStatus
import com.example.team42fitness.data.foodData.Nutrients
import com.google.android.material.progressindicator.CircularProgressIndicator

class FoodSearchFragment: Fragment(R.layout.fragment_food_search) {
    private val foodDataSearchService = FoodDataSearchService.create()
    private val repository = FoodDataSearchRepository(FoodDataSearchService.create())
    private val viewModel: FoodSearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // the loading and error indicators left a wierd empty spot in the UI. Need to find better
        // solution before including them.
        // val loadingIndicator = view.findViewById<CircularProgressIndicator>(R.id.loading_indicator)
        // val error = view.findViewById<TextView>(R.id.tv_loading_error)

        val foodInput = view.findViewById<EditText>(R.id.et_food_search_entry)
        val searchFoodBtn = view.findViewById<Button>(R.id.btn_search_food_data)

        val foodDataListRV: RecyclerView = view.findViewById(R.id.rv_food_data)
        foodDataListRV.layoutManager = LinearLayoutManager(requireContext())
        foodDataListRV.setHasFixedSize(true)

        val adapter = NutritionAdapter()
        foodDataListRV.adapter = adapter

        viewModel.searchResults.observe(viewLifecycleOwner) { searchResults ->
            adapter.updateFoodItems(searchResults?.foodsList)

        }

//        viewModel.loadingStatus.observe(viewLifecycleOwner){ loadingStatus ->
//            when(loadingStatus){
//                LoadingStatus.LOADING -> {
//                    loadingIndicator.visibility = View.VISIBLE
//                    foodDataListRV.visibility = View.INVISIBLE
//                    error.visibility = View.INVISIBLE
//                }
//                LoadingStatus.ERROR ->{
//                    loadingIndicator.visibility = View.INVISIBLE
//                    foodDataListRV.visibility = View.INVISIBLE
//                    error.visibility = View.VISIBLE
//                }
//                else -> {
//                    loadingIndicator.visibility = View.INVISIBLE
//                    foodDataListRV.visibility = View.VISIBLE
//                    error.visibility = View.INVISIBLE
//                }
//            }
//        }
//
//        viewModel.errorMessage.observe(viewLifecycleOwner){ errorMessage ->
//            if (errorMessage != null){
//                error.text = "Error: $errorMessage"
//            }
//        }


        searchFoodBtn.setOnClickListener {
            val query = foodInput.text.toString()
            if (!TextUtils.isEmpty(query)) {
                // perform search query
                viewModel.loadSearchResults(query)
                Log.d("FoodSearchFragment", "Perform search query for ${foodInput.text}")
                foodDataListRV.scrollToPosition(0)
            }
        }
    }
}
