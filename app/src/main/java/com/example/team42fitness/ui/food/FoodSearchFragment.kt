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
import com.example.team42fitness.data.foodData.FoodData

class FoodSearchFragment: Fragment(R.layout.fragment_food_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val foodInput = view.findViewById<EditText>(R.id.et_food_search_entry)
        val searchFoodBtn = view.findViewById<Button>(R.id.btn_search_food_data)

        val foodDataListRV: RecyclerView = view.findViewById(R.id.rv_food_data)
        foodDataListRV.layoutManager = LinearLayoutManager(requireContext())
        foodDataListRV.setHasFixedSize(true)

        val adapter = FoodDataAdapter()
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
        FoodData(1, "desc 1", "category 1"),
        FoodData(2, "desc 2", "category 2"),
        FoodData(3, "desc 3", "category 3"),
        FoodData(4, "desc 4", "category 4"),
        FoodData(5, "desc 5", "category 5"),
        FoodData(6, "desc 6", "category 6"),
        FoodData(7, "desc 7", "category 7"),
        FoodData(8, "desc 8", "category 8"),
        FoodData(9, "desc 9", "category 9"),
        FoodData(10, "desc 10", "category 10"),
        FoodData(12, "desc 11", "category 11"),



    )


}