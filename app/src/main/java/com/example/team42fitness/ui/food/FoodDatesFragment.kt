package com.example.team42fitness.ui.food

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.R
import com.example.team42fitness.data.foodData.FoodDate
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/*
this will be the main page of the list of food. it will display a list (using recyclerview) of days and a short
* description (maybe of food eaten or nutrition values). each item in the list will be clickable to display another fragment of
* each food items added for that day.
* */
class FoodDatesFragment : Fragment(R.layout.fragment_food) {
    private val viewModel: FoodViewModel by viewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        createDatesList()


        val foodDateListRV: RecyclerView = view.findViewById(R.id.rv_food_list)
        foodDateListRV.layoutManager = LinearLayoutManager(requireContext())
        foodDateListRV.setHasFixedSize(true)

        val adapter = FoodAdapter(::onFoodDateItemClick)
        foodDateListRV.adapter = adapter


        val dates: List<Date> = createDatesList()



        for (date in dates) {
            adapter.addFoodDate(FoodDate(date.toLocaleString().toString().slice(0..11)))
        }

    }

    private fun onFoodDateItemClick(){
        val directions = FoodDatesFragmentDirections.navigateToFoodData()

        findNavController().navigate(directions)
    }


    private fun createDatesList(): MutableList<Date>{


        val calender = Calendar.getInstance()
        val calender2 = Calendar.getInstance()

        val datesList: MutableList<Date> = mutableListOf()
        val dateFormat: DateFormat = SimpleDateFormat("MM-dd-yyyy")

        var date1: Date? = null
        var date2: Date? = null

        try{

            date1 = dateFormat.parse("03-17-2023")
            date2 = dateFormat.parse("04-15-2023")


        }catch (e: ParseException){
            e.printStackTrace()
        }

        calender.time = date1
        calender2.time = date2



        while(!calender.after(calender2)){
            datesList.add(calender.time)
            calender.add(Calendar.DATE, 1)
        }

        return datesList
    }




}



