package com.example.team42fitness.ui.food

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.R
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.team42fitness.api.food.FoodItem
import com.example.team42fitness.api.food.Nutrients

class NutritionAdapter: Adapter<NutritionAdapter.FoodDataViewHolder>(){
    private var foodItem = listOf<FoodItem>()

    fun updateFoodItems(newFoodItemList: List<FoodItem>?){
        foodItem = newFoodItemList ?: listOf()
        notifyDataSetChanged()

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

    class FoodDataViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val foodDataTV = view.findViewById<TextView>(R.id.tv_data_text)
        private val addFoodBtn = view.findViewById<Button>(R.id.btn_add_food_data)
        private val brand = view.findViewById<TextView>(R.id.tv_brand)
        private val calories = view.findViewById<TextView>(R.id.tv_calories)
        private val protein = view.findViewById<TextView>(R.id.tv_protein)
        private val fat = view.findViewById<TextView>(R.id.tv_fat)
        private val carb = view.findViewById<TextView>(R.id.tv_carb)
        private val sugar = view.findViewById<TextView>(R.id.tv_sugar)

        private var currentFoodItem: FoodItem? = null

        fun bind(foodItem: FoodItem){
            currentFoodItem = foodItem
            foodDataTV.text = foodItem.description
            brand.text = foodItem.brandName
            val nutrients: List<Nutrients> = foodItem.nutrients
            for ((i, nutrient) in nutrients.withIndex()){
                when (nutrient.name){
                    "Energy" -> {
                        val n = "Calories ${nutrient.amount} ${nutrient.unit}"
                        calories.text = n
                    }
                    "Protein" -> {
                        val n = "${nutrient.name} ${nutrient.amount} ${nutrient.unit}"
                        protein.text = n
                    }

                    "Total lipid (fat)" -> {
                        val n = "Fat ${nutrient.amount} ${nutrient.unit}"
                        fat.text = n
                    }

                    "Carbohydrate, by difference" -> {
                        val n = "Carbs ${nutrient.amount} ${nutrient.unit}"
                        carb.text = n
                    }

                    "Sugars" -> {
                        val n = "${nutrient.name} ${nutrient.amount} ${nutrient.unit}"
                        sugar.text = n
                    }
                }
            }

            addFoodBtn.setOnClickListener{
                Log.d("FoodDataAdapter","add food to database: $currentFoodItem")
            }
        }
    }


}


class NutritionAdapter2: Adapter<NutritionAdapter2.NutritionViewHolder2>() {
    private var foodItem = listOf<FoodItem>()

    fun updateFoodItems(newFoodItemList: List<FoodItem>?) {
        foodItem = newFoodItemList ?: listOf()
        notifyDataSetChanged()
    }

    override fun getItemCount() = foodItem.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutritionViewHolder2 {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.food_data,
            parent, false
        )
        return NutritionViewHolder2(view)
    }

    override fun onBindViewHolder(holder: NutritionViewHolder2, position: Int) {
        holder.bind(foodItem[position])

    }

    class NutritionViewHolder2(view: View) : RecyclerView.ViewHolder(view) {
        private val foodName = view.findViewById<TextView>(R.id.tv_food_name)
        private val foodImg = view.findViewById<ImageView>(R.id.iv_image)
        private val protein = view.findViewById<TextView>(R.id.tv_protein)
        private val fat = view.findViewById<TextView>(R.id.tv_fat)
        private val carb = view.findViewById<TextView>(R.id.tv_carb)
        private val sugar = view.findViewById<TextView>(R.id.tv_sugar)
        private val calories = view.findViewById<TextView>(R.id.tv_calories)

        private var currentFoodItem: FoodItem? = null

        init {
            foodImg.animate().apply {
                duration = 1000
                rotationXBy(360f)
            }
        }

        fun bind(foodItem: FoodItem) {
            currentFoodItem = foodItem
            foodName.text = foodItem.description
            val nutrients: List<Nutrients> = foodItem.nutrients
            for ((i, nutrient) in nutrients.withIndex()) {
                when (nutrient.name) {
                    "Energy" -> {
                        val n = "Calories ${nutrient.amount} ${nutrient.unit}"
                        calories.text = n
                    }
                    "Protein" -> {
                        val n = "${nutrient.name} ${nutrient.amount} ${nutrient.unit}"
                        protein.text = n
                    }

                    "Total lipid (fat)" -> {
                        val n = "Fat ${nutrient.amount} ${nutrient.unit}"
                        fat.text = n
                    }
                    "Carbohydrate, by difference" -> {
                        val n = "Carbs ${nutrient.amount} ${nutrient.unit}"
                        carb.text = n
                    }

                    "Sugars" -> {
                        val n = "${nutrient.name} ${nutrient.amount} ${nutrient.unit}"
                        sugar.text = n
                    }
                }
            }
        }
    }
}
