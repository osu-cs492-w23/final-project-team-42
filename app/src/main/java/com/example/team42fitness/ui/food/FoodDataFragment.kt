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



        val addFoodBtn = view.findViewById<Button>(R.id.btn_add_food_data)

        val foodDateListRV: RecyclerView = view.findViewById(R.id.rv_food_data)
        foodDateListRV.layoutManager = LinearLayoutManager(requireContext())
        foodDateListRV.setHasFixedSize(true)

//        val adapter = FoodDataAdapter(::onFoodSearchClick)
//        foodDateListRV.adapter = adapter


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
}