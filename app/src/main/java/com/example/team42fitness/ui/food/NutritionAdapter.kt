package com.example.team42fitness.ui.food

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.R
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.team42fitness.data.foodData.FoodItem

class NutritionAdapter: Adapter<NutritionAdapter.FoodDataViewHolder>(){

    private var foodItem = listOf<FoodItem>()


    fun updateFoodItems(newFoodItemList: List<FoodItem>?){
        foodItem = newFoodItemList ?: listOf()
    }

    override fun getItemCount() = foodItem.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodDataViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_data_item,
            parent, false)
        return FoodDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodDataViewHolder, position: Int){
        holder.bind(foodItem[position])

    }

//    fun addFood(food: FoodData){
//        foodData.add(0, food)
//        notifyItemInserted(0)
//    }

    class FoodDataViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val foodDataTV = view.findViewById<TextView>(R.id.tv_data_text)
        private val addFoodBtn = view.findViewById<Button>(R.id.btn_add_food_data)

        private var currentFoodItem: FoodItem? = null

//        init {
//            addFoodBtn.setOnClickListener{
//            }
//        }

        fun bind(foodItem: FoodItem){
            currentFoodItem = foodItem
            foodDataTV.text = foodItem.description
            addFoodBtn.setOnClickListener{
                Log.d("FoodDataAdapter","add food to database: $currentFoodItem")
            }
        }
    }
}


class NutritionAdapter2: Adapter<NutritionAdapter2.NutritionViewHolder2>(){

    private var foodItem = listOf<FoodItem>()


    fun updateFoodItems(newFoodItemList: List<FoodItem>?){
        foodItem = newFoodItemList ?: listOf()
    }

    override fun getItemCount() = foodItem.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutritionViewHolder2{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_data,
            parent, false)
        return NutritionViewHolder2(view)
    }

    override fun onBindViewHolder(holder: NutritionViewHolder2, position: Int){
        holder.bind(foodItem[position])

    }

//    fun addFood(food: FoodData){
//        foodData.add(0, food)
//        notifyItemInserted(0)
//    }

    class NutritionViewHolder2(view: View): RecyclerView.ViewHolder(view){
        private val foodName = view.findViewById<TextView>(R.id.tv_food_name)
        private val foodImg = view.findViewById<ImageView>(R.id.iv_image)
        private val nutrientName = view.findViewById<TextView>(R.id.tv_food_nutrient_name)
        private val nutrientUnit = view.findViewById<TextView>(R.id.tv_food_nutrient_unit)
        private val nutrientAmount = view.findViewById<TextView>(R.id.tv_food_nutrient_amount)


        private val layout = view.findViewById<CoordinatorLayout>(R.id.coordinator_layout)



        private var currentFoodItem: FoodItem? = null

        init {
                foodImg.animate().apply {
                    duration = 1000
                    rotationXBy(360f)
                }
        }

        fun bind(foodItem: FoodItem){
            currentFoodItem = foodItem
            foodName.text = foodItem.description
            nutrientName.text = foodItem.nutrients.name
            nutrientUnit.text = foodItem.nutrients.unit
            nutrientAmount.text = foodItem.nutrients.amount.toString()

        }
    }
}
