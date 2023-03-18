package com.example.team42fitness.ui.food

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.R
import com.example.team42fitness.data.foodData.FoodData
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.google.android.material.internal.ContextUtils.getActivity
import org.w3c.dom.Text

class FoodDataAdapter: Adapter<FoodDataAdapter.FoodDataViewHolder>(){

//    private var foodData: MutableList<FoodData> = mutableListOf()
    private var foodData = listOf<FoodData>()


    fun updateFoodItems(newFoodDataList: List<FoodData>?){
        foodData = newFoodDataList ?: listOf()
    }

    override fun getItemCount() = foodData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodDataViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_data_item,
            parent, false)
        return FoodDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodDataViewHolder, position: Int){
        holder.bind(foodData[position])

    }

//    fun addFood(food: FoodData){
//        foodData.add(0, food)
//        notifyItemInserted(0)
//    }

    class FoodDataViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val foodDataTV = view.findViewById<TextView>(R.id.tv_data_text)
        private val addFoodBtn = view.findViewById<Button>(R.id.btn_add_food_data)

        private var currentFoodData: FoodData? = null

//        init {
//            addFoodBtn.setOnClickListener{
//            }
//        }

        fun bind(foodData: FoodData){
            currentFoodData = foodData
            foodDataTV.text = foodData.description
            addFoodBtn.setOnClickListener{
                Log.d("FoodDataAdapter","add food to database: $currentFoodData")
            }
        }
    }
}


// stopped on setting up another adapter to add dummy data to FoodDataFragment and
// getting displaying picture of food, name, category etc. that will be in food_data.xml
class FoodDataAdapter2: Adapter<FoodDataAdapter2.FoodDataViewHolder2>(){

    //    private var foodData: MutableList<FoodData> = mutableListOf()
    private var foodData = listOf<FoodData>()


    fun updateFoodItems(newFoodDataList: List<FoodData>?){
        foodData = newFoodDataList ?: listOf()
    }

    override fun getItemCount() = foodData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodDataViewHolder2{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_data,
            parent, false)
        return FoodDataViewHolder2(view)
    }

    override fun onBindViewHolder(holder: FoodDataViewHolder2, position: Int){
        holder.bind(foodData[position])

    }

//    fun addFood(food: FoodData){
//        foodData.add(0, food)
//        notifyItemInserted(0)
//    }

    class FoodDataViewHolder2(view: View): RecyclerView.ViewHolder(view){
        private val foodDataTV = view.findViewById<TextView>(R.id.tv_food_name)
        private val foodCategoryTV = view.findViewById<TextView>(R.id.tv_food_category)
        private val foodImg = view.findViewById<ImageView>(R.id.iv_image)




        private var currentFoodData: FoodData? = null

        init {
                foodImg.animate().apply {
                    duration = 1000
                    rotationXBy(360f)
                }
        }

        fun bind(foodData: FoodData){
            currentFoodData = foodData
            foodDataTV.text = foodData.description
            foodCategoryTV.text = foodData.category
        }
    }
}
