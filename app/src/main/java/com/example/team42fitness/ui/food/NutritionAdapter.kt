package com.example.team42fitness.ui.food

import android.app.Application
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.R
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.team42fitness.api.food.FoodItem
import com.example.team42fitness.api.food.Nutrients
import com.example.team42fitness.data.food.Food
import java.util.*

class NutritionAdapter: Adapter<NutritionAdapter.FoodDataViewHolder>(){
    private var foodItem = listOf<FoodItem>()
    var date: String? = null


    fun updateFoodItems(newFoodItemList: List<FoodItem>?){
        foodItem = newFoodItemList ?: listOf()
        notifyDataSetChanged()

    }

    override fun getItemCount() = foodItem.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodDataViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_data_item,
            parent, false)
        return FoodDataViewHolder(view, date)
    }

    override fun onBindViewHolder(holder: FoodDataViewHolder, position: Int){
        holder.bind(foodItem[position])

    }

    class FoodDataViewHolder(view: View, date:String?): RecyclerView.ViewHolder(view){
        private val foodDataTV = view.findViewById<TextView>(R.id.tv_data_text)
        private val addFoodBtn = view.findViewById<Button>(R.id.btn_add_food_data)
        private val brand = view.findViewById<TextView>(R.id.tv_brand)
        private val calories = view.findViewById<TextView>(R.id.tv_calories)
        private val protein = view.findViewById<TextView>(R.id.tv_protein)
        private val fat = view.findViewById<TextView>(R.id.tv_fat)
        private val carb = view.findViewById<TextView>(R.id.tv_carb)
        private val sugar = view.findViewById<TextView>(R.id.tv_sugar)

        private var currentFoodItem: FoodItem? = null
//        private val viewModel: NutritionViewModel =
//            ViewModelProvider.AndroidViewModelFactory().create(NutritionViewModel::class.java )

        fun bind(foodItem: FoodItem){
            currentFoodItem = foodItem
            foodDataTV.text = foodItem.description
            brand.text = foodItem.brandName
            val nutrients: List<Nutrients> = foodItem.nutrients

            var energy: String? = null
            var proteinStr: String? = null
            var fatStr: String? = null
            var carbsStr: String? = null
            var sugarStr: String? = null
            val date = NutritionAdapter2().getClickedDate()



            for ((i, nutrient) in nutrients.withIndex()){
                when (nutrient.name){
                    "Energy" -> {
                        val n = "Calories ${nutrient.amount} ${nutrient.unit}"
                        energy = n
                        calories.text = n
                    }
                    "Protein" -> {
                        val n = "${nutrient.name} ${nutrient.amount} ${nutrient.unit}"
                        proteinStr = n
                        protein.text = n
                    }

                    "Total lipid (fat)" -> {
                        val n = "Fat ${nutrient.amount} ${nutrient.unit}"
                        fatStr = n
                        fat.text = n
                    }

                    "Carbohydrate, by difference" -> {
                        val n = "Carbs ${nutrient.amount} ${nutrient.unit}"
                        carbsStr = n
                        carb.text = n
                    }

                    "Sugars, total including NLEA" -> {
                        val n = "Sugars ${nutrient.amount} ${nutrient.unit}"
                        sugarStr = n
                        sugar.text = n
                    }
                }
            }
            addFoodBtn.setOnClickListener{

                Log.d("N1","date = $date")
                val food = Food(fdcid = currentFoodItem!!.fdcId, date = date, energy = energy
                , protein = proteinStr, fat = fatStr, carbs = carbsStr, sugars = sugarStr, name = currentFoodItem!!.description)
                val nutrition = NutritionViewModel(application = Application())
                nutrition.addFoodItem(food)

                Log.d("FoodDataAdapter","add food to database: $currentFoodItem")
            }
        }
    }


}


class NutritionAdapter2: Adapter<NutritionAdapter2.NutritionViewHolder2>() {
    private var foodItem = listOf<Food>()
    var date: String? = null

    fun updateFoodItems(newFoodItemList: List<Food>?, newDate: String?) {
        foodItem = newFoodItemList ?: listOf()
        date = newDate
        notifyDataSetChanged()
    }

    fun getClickedDate(): String?{
        return date
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

        private var currentFoodItem: Food? = null

        init {
            foodImg.animate().apply {
                duration = 1000
                rotationXBy(360f)
            }
        }

        fun bind(food: Food) {
            currentFoodItem = food
            foodName.text = food.name
            protein.text = food.protein
            fat.text = food.fat
            carb.text = food.carbs
            sugar.text = food.sugars
            calories.text = food.energy

//            val nutrients: List<Nutrients> = food.nutrients
//            for ((i, nutrient) in nutrients.withIndex()) {
//                when (nutrient.name) {
//                    "Energy" -> {
//                        val n = "Calories ${nutrient.amount} ${nutrient.unit}"
//                        calories.text = n
//                    }
//                    "Protein" -> {
//                        val n = "${nutrient.name} ${nutrient.amount} ${nutrient.unit}"
//                        protein.text = n
//                    }
//
//                    "Total lipid (fat)" -> {
//                        val n = "Fat ${nutrient.amount} ${nutrient.unit}"
//                        fat.text = n
//                    }
//                    "Carbohydrate, by difference" -> {
//                        val n = "Carbs ${nutrient.amount} ${nutrient.unit}"
//                        carb.text = n
//                    }
//
//                    "Sugars" -> {
//                        val n = "${nutrient.name} ${nutrient.amount} ${nutrient.unit}"
//                        sugar.text = n
//                    }
//                }
//            }
        }
    }
}
