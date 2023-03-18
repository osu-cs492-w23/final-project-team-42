package com.example.team42fitness.ui.food

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.R
import com.example.team42fitness.data.foodData.FoodData
import androidx.recyclerview.widget.RecyclerView.Adapter

class FoodDataAdapter(private val onSearchClick: ()-> Unit){
//    : Adapter<FoodDataAdapter.FoodDataViewHolder>(){
//
//    private val foodData: MutableList<FoodData> = mutableListOf()
//
//
//    override fun getItemCount() = foodData.size
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodDataViewHolder{
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_data_item,
//            parent, false)
//        return FoodDataViewHolder(view, onSearchClick)
//    }
//
//    override fun onBindViewHolder(holder: FoodDataViewHolder, position: Int){
//        holder.bind(foodData[position])
//    }
//
//    fun addFood(food: FoodData){
//        foodData.add(0, food)
//        notifyItemInserted(0)
//    }
//
//    class FoodDataViewHolder(view: View, private val onSearchClick: () -> Unit): RecyclerView.ViewHolder(view){
//        private val foodDataTV = view.findViewById<TextView>(R.id.tv_data_text)
//        private var currentFoodData: FoodData? = null
//
//        init {
//            view.setOnClickListener{
//                onSearchClick()
//            }
//        }
//
//        fun bind(foodData: FoodData){
//            currentFoodData = foodData
//            foodDataTV.text = foodData.description
//        }
//    }
}