package com.example.team42fitness.ui.food

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.team42fitness.R
import com.example.team42fitness.data.foodData.FoodDate

class FoodAdapter: Adapter<FoodAdapter.FoodDateViewHolder>() {
    val foodDates: MutableList<FoodDate> = mutableListOf()

    override fun getItemCount() = foodDates.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodDateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_list_item,
        parent, false)

        return FoodDateViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodDateViewHolder, position: Int) {
        holder.bind(foodDates[position])
    }

    fun addFoodDate(foodDate: FoodDate){
        foodDates.add(0, foodDate)
        notifyItemInserted(0)
    }

    class FoodDateViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val foodDateTV = view.findViewById<TextView>(R.id.tv_date_text)
        private var currentDate: FoodDate? = null

        fun bind(foodDate: FoodDate){
            currentDate = foodDate
            foodDateTV.text = foodDate.text
        }
    }
}