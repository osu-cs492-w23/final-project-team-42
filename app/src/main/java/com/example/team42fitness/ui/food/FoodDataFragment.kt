package com.example.team42fitness.ui.food

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.R
import com.example.team42fitness.data.foodData.FoodData

class FoodDataFragment: Fragment(R.layout.fragment_food_data) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val addFoodBtn = view.findViewById<Button>(R.id.btn_add_food_data) //change name here

        val foodDateListRV: RecyclerView = view.findViewById(R.id.rv_food_data)
        foodDateListRV.layoutManager = LinearLayoutManager(requireContext())
        foodDateListRV.setHasFixedSize(true)

        val adapter = FoodDataAdapter2() // create new adapter
        foodDateListRV.adapter = adapter


        adapter.updateFoodItems(dummyResults)

        addFoodBtn.setOnClickListener{

//            adapter.addFood(FoodData(1, "new Food", "category"))
//            foodDateListRV.scrollToPosition(0)
            onFoodSearchClick()

        }

    }

    // create another adapter to handle clicks on each food item

    private fun onFoodSearchClick(){
        val directions = FoodDataFragmentDirections.navigateToSearchFood()
        findNavController().navigate(directions)
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