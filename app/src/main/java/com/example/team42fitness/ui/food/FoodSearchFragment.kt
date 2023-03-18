package com.example.team42fitness.ui.food

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.R

class FoodSearchFragment: Fragment(R.layout.fragment_food_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val foodInput = view.findViewById<EditText>(R.id.et_food_search_entry)
        val searchFoodBtn = view.findViewById<Button>(R.id.btn_search_food_data)

        val foodDateListRV: RecyclerView = view.findViewById(R.id.rv_food_data)
        foodDateListRV.layoutManager = LinearLayoutManager(requireContext())
        foodDateListRV.setHasFixedSize(true)


        searchFoodBtn.setOnClickListener{
            // perform search query
            Log.d("FoodSearchFragment", "Perform search query for ${foodInput.text}")

        }


    }
}