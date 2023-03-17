package com.example.team42fitness.ui.food

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.R
import com.example.team42fitness.data.foodData.FoodDate


/*
this will be the main page of the list of food. it will display a list (using recyclerview) of days and a short
* description of the food eaten or nutrition. each item in the list will be clickable to display another fragment of
* each food items added for that day.
* */
class FoodFragment : Fragment(R.layout.fragment_food) {
    private val viewModel: FoodViewModel by viewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dateEntry = view.findViewById<EditText>(R.id.et_date_entry)
        val addDateBtn = view.findViewById<Button>(R.id.btn_add_date)
        val foodDateListRV: RecyclerView = view.findViewById(R.id.rv_food_list)
        foodDateListRV.layoutManager = LinearLayoutManager(requireContext())
        foodDateListRV.setHasFixedSize(true)

        val adapter = FoodAdapter(::onFoodDateItemClick)
        foodDateListRV.adapter = adapter



        addDateBtn.setOnClickListener{
            // get string rep of text
            val dateText = dateEntry.text.toString()

            if (!TextUtils.isEmpty(dateText)){
                //add to recycler view
                adapter.addFoodDate(FoodDate(dateText))
                foodDateListRV.scrollToPosition(0)
                dateEntry.setText("")
            }


        }
    }

    private fun onFoodDateItemClick(fooddate: FoodDate){
        val directions = FoodFragmentDirections.navigateToFoodData(fooddate)
        findNavController().navigate(directions)
    }
}